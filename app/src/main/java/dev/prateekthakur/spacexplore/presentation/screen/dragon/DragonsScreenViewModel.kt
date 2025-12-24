package dev.prateekthakur.spacexplore.presentation.screen.dragon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.Dragon
import dev.prateekthakur.spacexplore.domain.repository.DragonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DragonsScreenViewModel @Inject constructor(repository: DragonRepository) : ViewModel() {

    private val _state = MutableStateFlow<List<Dragon>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = repository.getAll()
        }
    }
}