package dev.prateekthakur.spacexplore.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.spacexplore.domain.models.EventHistory
import dev.prateekthakur.spacexplore.domain.repository.EventHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventHistoryScreenViewModel @Inject constructor(private val eventHistoryRepository: EventHistoryRepository) : ViewModel(){

    private val _state = MutableStateFlow<List<EventHistory>?>(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = eventHistoryRepository.getAll().sortedByDescending { it.eventDateUnix }
        }
    }
}