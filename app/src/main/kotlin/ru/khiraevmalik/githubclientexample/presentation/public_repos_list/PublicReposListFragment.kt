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
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.presentation.base.BaseFragment
import ru.khiraevmalik.githubclientexample.presentation.base.PagingStatus
import ru.khiraevmalik.githubclientexample.presentation.base.ViewModelFactory
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListAdapter
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListItem
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.State
import ru.khiraevmalik.githubclientexample.presentation.utils.addSystemTopPadding
import ru.khiraevmalik.githubclientexample.presentation.utils.visibleWithCheck

class PublicReposListFragment : BaseFragment(R.layout.fragment_public_repos_list) {

    private val vm by lazy {
        ViewModelProvider(this, ViewModelFactory).get(PublicReposListViewModel::class.java)
    }

    private val adapter = ReposListAdapter()

    companion object {
        fun newInstance() = PublicReposListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeState()
        initViews()
    }

    private fun initViews() {
        fragment_public_repos_list_recycler_view.adapter = adapter
        fragment_public_repos_list_recycler_view.setHasFixedSize(true)
        fragment_public_repos_list_status_bar.addSystemTopPadding()
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
                    adapter.submitList(state.repos + tail)
                }
                is State.Loading -> {
                    // TODO
                }
                is State.EmptyData -> {
                    // TODO
                }
                is State.Error -> {
                    // TODO
                }

            }
            fragment_public_repos_list_recycler_view.visibleWithCheck(state is State.Success)
            fragment_public_repos_list_error_loading_repos_stub.visibleWithCheck(state is State.Error)
            fragment_public_repos_list_no_data_with_retry_stub.visibleWithCheck(state is State.EmptyData)
            fragment_public_repos_list_progress_bar.visibleWithCheck(state is State.Loading)
        })
    }

}

