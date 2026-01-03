package dev.prateekthakur.spacexplore.presentation.screen.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.Launch
import dev.prateekthakur.spacexplore.domain.repository.LaunchesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesScreenViewModel @Inject constructor(private val repository: LaunchesRepository): ViewModel(){
    private val _state = MutableStateFlow<List<Launch>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = repository.getAll()
        }
    }
}