package ru.khiraevmalik.githubclientexample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.base.ViewModelFactory
import ru.khiraevmalik.githubclientexample.routing.ContainerHolder

class RootActivity : AppCompatActivity() {

    private val vm: RootViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(RootViewModel::class.java)
    }

    private val containerHolder by lazy {
        ContainerHolder(
                R.id.main_container,
                supportFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.attachContainerHolder(containerHolder)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) coldStart()
    }

    override fun onDestroy() {
        vm.detachContainerHolder()
        super.onDestroy()
    }

    private fun coldStart() {
        vm.openReposListFragment()
    }

}
