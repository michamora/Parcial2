package com.ucne.parcial2.ui.tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.repository.TicketApiRepositoryImp
import com.ucne.parcial2.data.repository.TicketRepository
import com.ucne.parcial2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TicketsListState(
    val isLoading: Boolean = false,
    val tickets: List<TicketDto> = emptyList(),
    val error: String = ""
)
data class TicketsState(
    val isLoading: Boolean = false,
    val ticket: TicketDto ? =  null,
    val error: String = ""
)
@HiltViewModel
class TicketViewModel @Inject constructor(

    private val ticketRepository: TicketApiRepositoryImp
) : ViewModel() {
    var ticketId by mutableStateOf(0)
    var asunto by mutableStateOf("")
    var asuntoError by mutableStateOf("")

    var empresa by mutableStateOf("")
    var empresaError by mutableStateOf("")

    var encargadoId by mutableStateOf("")

    var especificaciones by mutableStateOf("")
    var especificacionesError by mutableStateOf("")

    var estatus by mutableStateOf("")

    var fecha by mutableStateOf("")

    var orden by mutableStateOf("")

    val tipoEstatus = listOf("Solicitado", "En espera", "Finalizado")
    var uiState = MutableStateFlow(TicketsListState())
        private set
    var uiStateTicket = MutableStateFlow(TicketsState())
        private set

    fun onAsuntoChanged(asunto: String) {
        this.asunto = asunto
        HayErrores()
    }
    fun onEmpresaChanged(empresa: String) {
        this.empresa = empresa
        HayErrores()
    }
    fun onEspecificacionesChanged(especificaciones: String) {
        this.especificaciones = especificaciones
        HayErrores()
    }

     fun HayErrores(): Boolean {
        var hayError = false
        asuntoError = ""
        if (asunto.isBlank()) {
            asuntoError = "  Debe indicar el asunto"
            hayError = true
        }

        empresaError = ""
        if (empresa.isBlank()) {
            empresaError = "  Debe indicar la empresa"
            hayError = true
        }

        especificacionesError = ""
        if (especificaciones.isBlank()) {
            especificacionesError = "  Debe indicar las especificaciones"
            hayError = true
        }


        return hayError
    }

    fun limpiar() {

        Limpiar()
    }

    private fun Limpiar() {

        asunto = ""
        empresa = ""
        encargadoId = ""
        especificaciones = ""
        estatus = ""
        fecha = ""
        orden = ""

    }

    fun setTicket(id:Int){
        ticketId = id
        Limpiar()
        ticketRepository.getTicketbyId(ticketId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateTicket.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateTicket.update {
                        it.copy(ticket = result.data )
                    }
                    empresa = uiStateTicket.value.ticket!!.empresa
                    asunto = uiStateTicket.value.ticket!!.asunto
                    estatus = uiStateTicket.value.ticket!!.estatus
                    especificaciones = uiStateTicket.value.ticket!!.especificaciones
                }
                is Resource.Error -> {
                    uiStateTicket.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
    fun putTicket(){
        viewModelScope.launch {
            ticketRepository.putTicket(ticketId, TicketDto(asunto,
                empresa,
                uiStateTicket.value.ticket!!.encargadoId,
                especificaciones,
                estatus,uiStateTicket.value.ticket!!.fecha,
                uiStateTicket.value.ticket!!.orden,
                ticketId = ticketId ))
        }

    }
    init {
        ticketRepository.getTickets().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(tickets = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

}