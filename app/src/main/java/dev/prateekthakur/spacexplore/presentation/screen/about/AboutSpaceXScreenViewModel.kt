package dev.prateekthakur.spacexplore.presentation.screen.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.AboutSpaceX
import dev.prateekthakur.spacexplore.domain.repository.AboutSpaceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutSpaceXScreenViewModel @Inject constructor(private val aboutSpaceXRepository: AboutSpaceXRepository) : ViewModel() {

    private val _info = MutableStateFlow<AboutSpaceX?>(null)
    val info = _info.asStateFlow()

    init {
        viewModelScope.launch {
            _info.value = aboutSpaceXRepository.getInfo()
        }
    }
}