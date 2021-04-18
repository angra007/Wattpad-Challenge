package com.angraankit.www.wattpad_challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angraankit.www.wattpad_challenge.model.Story
import com.angraankit.www.wattpad_challenge.repository.MainRepository
import com.angraankit.www.wattpad_challenge.utill.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val mainRepository: MainRepository
    ) : ViewModel() {

    private val _datastate : MutableLiveData<DataState<List<Story>>> = MutableLiveData()
    val dataState : LiveData<DataState<List<Story>>>
        get() = _datastate

    fun setStateEvent (mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetStories -> {
                    mainRepository.getStories()
                        .onEach { dataState ->
                            _datastate.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class MainStateEvent {
    object GetStories : MainStateEvent()
}



