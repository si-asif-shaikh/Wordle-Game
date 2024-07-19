package com.uefa.wordle.core.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.uefa.wordle.core.business.interactor.InitializeGameUseCase
import com.uefa.wordle.core.navigation.AppComposeNavigator
import com.uefa.wordle.utils.BaseViewModel
import com.uefa.wordle.utils.UiEffect
import com.uefa.wordle.utils.UiEvent
import com.uefa.wordle.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject


@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val appComposeNavigator: AppComposeNavigator,
    private val initializeGameUseCase: InitializeGameUseCase
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>(
    savedStateHandle
) {

    override fun createInitialState(): MainContract.State {
        return MainContract.State()
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            else -> {}
        }
    }

    fun initGame() {
        viewModelScope.launch {
            when(val result = initializeGameUseCase.invoke()){
                InitializeGameUseCase.Result.Failure -> {

                }
                InitializeGameUseCase.Result.Success -> {
                    setState {
                        copy(
                            initializationComplete = true
                        )
                    }
                }
            }

        }
    }

}

internal class MainContract {

    sealed class Event() : UiEvent {

    }

    @Parcelize
    data class State(
        val arg: String = "",
        val initializationComplete:Boolean = false
    ) : UiState

    sealed class Effect() : UiEffect {}

}

