package ru.khiraevmalik.githubclientexample.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.khiraevmalik.githubclientexample.mvi_no_reactive.Store
import ru.khiraevmalik.githubclientexample.mvi_no_reactive.lifecycle.SingleLiveData

abstract class CoroutinesMviViewModel<A, U, E, S, N>(
        private val store: Store<A, U, E, S, N>
) : CoroutinesViewModel() where U : A, E : A {

    private val mState = MutableLiveData<S>()
    private val mEvents = SingleLiveData<N>()

    val state: LiveData<S> = mState
    val news: LiveData<N> = mEvents

    init {
        store.setOnStateChangeListener { newState ->
            mState.postValue(newState)
        }
        store.setEventsListener { event ->
            mEvents.postValue(event)
        }
    }

    override fun onCleared() {
        super.onCleared()
        store.dispose()
    }

    fun proceed(action: A) {
        store.proceed(action)
    }

}
