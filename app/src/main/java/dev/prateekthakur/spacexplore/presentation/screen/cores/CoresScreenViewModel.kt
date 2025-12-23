package dev.prateekthakur.spacexplore.presentation.screen.cores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.Core
import dev.prateekthakur.spacexplore.domain.repository.CoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoresScreenViewModel @Inject constructor(private val coreRepository: CoreRepository) : ViewModel() {

    private val _state = MutableStateFlow<List<Core>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = coreRepository.getAll()
        }
    }
}