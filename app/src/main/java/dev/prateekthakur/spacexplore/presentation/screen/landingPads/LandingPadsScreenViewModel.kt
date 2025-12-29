package dev.prateekthakur.spacexplore.presentation.screen.landingPads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.LandingPad
import dev.prateekthakur.spacexplore.domain.repository.LandingPadsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingPadsScreenViewModel @Inject constructor(private val landingPadsRepository: LandingPadsRepository) : ViewModel(){

    private val _state = MutableStateFlow<List<LandingPad>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = landingPadsRepository.getAll()
        }
    }
}