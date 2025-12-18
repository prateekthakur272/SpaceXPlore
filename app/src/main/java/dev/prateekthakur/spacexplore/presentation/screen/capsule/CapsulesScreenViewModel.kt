package dev.prateekthakur.spacexplore.presentation.screen.capsule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.Capsule
import dev.prateekthakur.spacexplore.domain.repository.CapsuleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CapsulesScreenViewModel @Inject constructor(private val capsuleRepository: CapsuleRepository) : ViewModel() {

    private val _state = MutableStateFlow<List<Capsule>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = capsuleRepository.getAll()
        }
    }
}