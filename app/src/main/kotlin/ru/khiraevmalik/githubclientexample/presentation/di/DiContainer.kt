package ru.khiraevmalik.githubclientexample.presentation.di

import ru.khiraevmalik.githubclientexample.presentation.routing.Navigator

object DiContainer {

    private val navigator by lazy {
        Navigator()
    }

    fun provideNavigator(): Navigator = navigator

}
