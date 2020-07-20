package ru.khiraevmalik.githubclientexample.presentation.public_repos_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_public_repos_list.fragment_public_repos_list_recycler_view
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.presentation.base.BaseFragment
import ru.khiraevmalik.githubclientexample.presentation.base.ViewModelFactory
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListAdapter
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.State

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
    }

    private fun observeState() {
        vm.state.observe(this, Observer { state ->
            when (state) {
                is State.Success -> {
                    // TODO
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
        })
    }

}

