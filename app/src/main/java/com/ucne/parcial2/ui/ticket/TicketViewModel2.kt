package com.ucne.parcial2.ui.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.repository.TicketApiRepository
import com.ucne.parcial2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class TicketListState(
    val isLoading: Boolean = false,
    val ticket: List<TicketDto> = emptyList(),
    val error: String = ""
)

@HiltViewModel
class TicketViewModel2 @Inject constructor(
    private val ticketApiRepository: TicketApiRepository
) : ViewModel() {

    var uiState = MutableStateFlow(TicketListState())
        private set

    init {
        ticketApiRepository.getTicket().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(ticket = result.data ?: emptyList())
                    }
                }

                is Resource.Error -> {
                    uiState.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}