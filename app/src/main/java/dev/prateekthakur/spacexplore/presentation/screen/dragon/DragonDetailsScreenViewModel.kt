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
class DragonDetailsScreenViewModel @Inject constructor(private val dragonRepository: DragonRepository) : ViewModel() {
    private val _state = MutableStateFlow<Dragon?>(null)
    val state = _state.asStateFlow()

    fun getDetails(id: String) {
        viewModelScope.launch {
            _state.value = dragonRepository.getSingle(id)
        }
    }
}