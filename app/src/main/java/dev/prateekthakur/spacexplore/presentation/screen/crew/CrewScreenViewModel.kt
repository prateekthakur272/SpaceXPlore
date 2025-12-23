package dev.prateekthakur.spacexplore.presentation.screen.crew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.Crew
import dev.prateekthakur.spacexplore.domain.repository.CrewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewScreenViewModel @Inject constructor(private val crewRepository: CrewRepository) : ViewModel() {

    private val _state = MutableStateFlow<List<Crew>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = crewRepository.getAll()
        }
    }
}