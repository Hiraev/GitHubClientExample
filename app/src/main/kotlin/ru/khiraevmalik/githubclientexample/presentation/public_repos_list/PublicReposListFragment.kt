package ru.khiraevmalik.githubclientexample.presentation.public_repos_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_public_repos_list.fragment_public_repos_list_error_loading_repos_stub
import kotlinx.android.synthetic.main.fragment_public_repos_list.fragment_public_repos_list_no_data_with_retry_stub
import kotlinx.android.synthetic.main.fragment_public_repos_list.fragment_public_repos_list_progress_bar
import kotlinx.android.synthetic.main.fragment_public_repos_list.fragment_public_repos_list_recycler_view
import kotlinx.android.synthetic.main.fragment_public_repos_list.fragment_public_repos_list_status_bar
import kotlinx.android.synthetic.main.include_error_loading_repos_stub.include_error_with_retry_stub_button
import kotlinx.android.synthetic.main.include_no_data_with_retry_stub.include_no_data_with_retry_stub_button
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.base.BaseFragment
import ru.khiraevmalik.githubclientexample.base.LinearLayoutManagerRecyclerViewOnScrollListener
import ru.khiraevmalik.githubclientexample.base.PagingStatus
import ru.khiraevmalik.githubclientexample.base.ViewModelFactory
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListAdapter
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListItem
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.Action
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.State
import ru.khiraevmalik.githubclientexample.utils.addSystemTopPadding
import ru.khiraevmalik.githubclientexample.utils.rippleClick
import ru.khiraevmalik.githubclientexample.utils.visibleWithCheck

class PublicReposListFragment : BaseFragment(R.layout.fragment_public_repos_list) {

    private val vm by lazy {
        ViewModelProvider(this, ViewModelFactory).get(PublicReposListViewModel::class.java)
    }

    private val adapter = ReposListAdapter()

    private val scrollListener = LinearLayoutManagerRecyclerViewOnScrollListener(PREFETCH_DISTANCE) {
        vm.proceed(Action.User.LoadMore)
    }

    companion object {
        const val PREFETCH_DISTANCE = 10
        fun newInstance() = PublicReposListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeState()
        initViews()
    }

    private fun initViews() {
        fragment_public_repos_list_recycler_view.adapter = adapter
        fragment_public_repos_list_recycler_view.setHasFixedSize(true)
        fragment_public_repos_list_recycler_view.addOnScrollListener(scrollListener)
        fragment_public_repos_list_status_bar.addSystemTopPadding()
        include_no_data_with_retry_stub_button.rippleClick {
            vm.proceed(Action.User.Retry)
        }
        include_error_with_retry_stub_button.rippleClick {
            vm.proceed(Action.User.Retry)
        }
        adapter.setOnRetryMoreClickListener {
            vm.proceed(Action.User.LoadMore)
        }
        adapter.setItemClickListener { item ->
            (item as? ReposListItem.Repo)?.let {
                vm.openRepo(it.info)
            }
        }
    }

    private fun observeState() {
        vm.state.observe(this, Observer { state ->
            when (state) {
                is State.Success -> {
                    val tail: List<ReposListItem> = when (state.pagingStatus) {
                        PagingStatus.LOADING -> listOf(ReposListItem.LoadingMore)
                        PagingStatus.ERROR -> listOf(ReposListItem.LoadingMoreError)
                        PagingStatus.FULL -> listOf(ReposListItem.FullRepos)
                        else -> emptyList()
                    }
                    if (state.pagingStatus == PagingStatus.HAS_MORE) scrollListener.enable() else scrollListener.disable()
                    adapter.submitList(state.repos + tail)
                }
            }
            fragment_public_repos_list_recycler_view.visibleWithCheck(state is State.Success)
            fragment_public_repos_list_error_loading_repos_stub.visibleWithCheck(state is State.Error)
            fragment_public_repos_list_no_data_with_retry_stub.visibleWithCheck(state is State.EmptyData)
            fragment_public_repos_list_progress_bar.visibleWithCheck(state is State.Loading)
        })
    }

}

