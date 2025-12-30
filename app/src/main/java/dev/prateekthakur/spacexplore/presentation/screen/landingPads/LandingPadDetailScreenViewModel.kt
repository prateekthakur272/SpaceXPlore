package dev.prateekthakur.spacexplore.presentation.screen.landingPads

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.LandingPad
import dev.prateekthakur.spacexplore.domain.repository.LandingPadsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale.getDefault
import javax.inject.Inject

@HiltViewModel
class LandingPadDetailScreenViewModel @Inject constructor(private val repository: LandingPadsRepository) : ViewModel() {
    private val _state = MutableStateFlow<LandingPad?>(null)
    val state = _state.asStateFlow()

    fun loadDetails(id: String){
        viewModelScope.launch {
            _state.value = repository.getSingle(id)
        }
    }
}