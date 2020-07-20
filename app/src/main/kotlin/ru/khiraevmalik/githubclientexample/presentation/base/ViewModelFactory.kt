package ru.khiraevmalik.githubclientexample.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.khiraevmalik.githubclientexample.presentation.RootViewModel
import ru.khiraevmalik.githubclientexample.presentation.di.DiContainer

object ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            when (modelClass) {
                RootViewModel::class.java -> RootViewModel(DiContainer.provideNavigator())
                else -> throw IllegalArgumentException("Can't find model")
            } as T

}
