package com.ucne.parcial2.ui.tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.repository.TicketApiRepositoryImp
import com.ucne.parcial2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val ticket: TicketDto? = null,
    val error: String = ""
)

@HiltViewModel
class TicketViewModel @Inject constructor(

    private val ticketRepository: TicketApiRepositoryImp
) : ViewModel() {

    var ticketId by mutableStateOf(0)

    var encargadoId by mutableStateOf("")

    var asunto by mutableStateOf("")
    var asuntoError by mutableStateOf("")

    var empresa by mutableStateOf("")
    var empresaError by mutableStateOf("")

    var especificaciones by mutableStateOf("")
    var especificacionesError by mutableStateOf("")

    var estatus by mutableStateOf("")
    var tipoEstatusError by mutableStateOf("")

    var fecha by mutableStateOf("")

    var orden by mutableStateOf("")

    val tipoEstatus = listOf("Solicitado", "En espera", "Finalizado", "")

    var uiState = MutableStateFlow(TicketsListState())
        private set
    var uiStateTicket = MutableStateFlow(TicketsState())
        private set


    fun onEmpresaChanged(empresa: String) {
        this.empresa = empresa
        HayErroresModificando()
    }

    fun onAsuntoChanged(asunto: String) {
        this.asunto = asunto
        HayErroresModificando()
    }

    fun onEspecificacionesChanged(especificaciones: String) {
        this.especificaciones = especificaciones
        HayErroresModificando()
    }

    fun onEstatusChanged(estatus: String) {
        this.estatus = estatus
        HayErroresModificando()
    }

    //----------------------------------------------------------------
    fun onEmpresa2Changed(empresa: String) {
        this.empresa = empresa
        HayErroresRegistrando()
    }

    fun onAsunto2Changed(asunto: String) {
        this.asunto = asunto
        HayErroresRegistrando()
    }

    fun onEspecificaciones2Changed(especificaciones: String) {
        this.especificaciones = especificaciones
        HayErroresRegistrando()
    }

    fun onEstatus2Changed(estatus: String) {
        this.estatus = estatus
        HayErroresRegistrando()
    }


    fun HayErroresModificando(): Boolean {

        var hayError = false

        empresaError = ""
        if (empresa.isBlank()) {
            hayError = true
        }

        asuntoError = ""
        if (asunto.isBlank()) {
            hayError = true
        }

        especificacionesError = ""
        if (especificaciones.isBlank()) {
            hayError = true
        }

        tipoEstatusError = ""
        if (estatus.isBlank()) {
            hayError = true
        }


        return hayError
    }

    fun HayErroresRegistrando(): Boolean {

        var hayError = false

        empresaError = ""
        if (empresa.isBlank()) {
            hayError = true
        }

        asuntoError = ""
        if (asunto.isBlank()) {
            hayError = true
        }

        especificacionesError = ""
        if (especificaciones.isBlank()) {
            hayError = true
        }

        tipoEstatusError = ""
        if (estatus.isBlank()) {
            hayError = true
        }

        return hayError
    }

    fun limpiarErrores() {

        empresaError = ""

        asuntoError = ""

        especificacionesError = ""

        tipoEstatusError = ""

    }

    private fun Limpiar() {

        empresa = ""
        asunto = ""
        especificaciones = ""
        estatus = ""

    }

    fun Clean() {

        empresa = ""
        asunto = ""
        especificaciones = ""
        estatus = ""

        limpiarErrores()

    }

    fun CleanRegistro() {

        empresa = ""
        asunto = ""
        especificaciones = ""
        estatus = ""

        limpiarErrores()

    }

    fun setTicket(id: Int) {
        ticketId = id
        Limpiar()
        ticketRepository.getTicketbyId(ticketId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateTicket.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateTicket.update {
                        it.copy(ticket = result.data)
                    }
                    empresa = uiStateTicket.value.ticket!!.empresa
                    asunto = uiStateTicket.value.ticket!!.asunto
                    fecha = uiStateTicket.value.ticket!!.fecha
                    estatus = uiStateTicket.value.ticket!!.estatus
                    especificaciones = uiStateTicket.value.ticket!!.especificaciones
                }
                is Resource.Error -> {
                    uiStateTicket.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putTicket() {
        viewModelScope.launch {
            ticketRepository.putTicket(
                ticketId, TicketDto(
                    asunto = asunto,
                    empresa = empresa,
                    uiStateTicket.value.ticket!!.encargadoId,
                    especificaciones = especificaciones,
                    estatus = estatus,
                    uiStateTicket.value.ticket!!.fecha,
                    uiStateTicket.value.ticket!!.orden,
                    ticketId = ticketId
                )
            )
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

    fun deleteTicket(id: Int) {
        viewModelScope.launch {
            ticketId = id!!
            try {
                if (ticketId != null) {
                    ticketRepository.deleteTicket(ticketId)
                } else {
                    throw NullPointerException("Value is null")
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }


    fun postTicketsNuevo() {
        viewModelScope.launch {
            ticketRepository.postTicket(
                TicketDto(
                    asunto = asunto,
                    empresa = empresa,
                    encargadoId = encargadoId.toIntOrNull() ?: 0,
                    especificaciones = especificaciones,
                    estatus = estatus,
                    fecha = "2023-03-31T01:04:28.866Z",
                    orden = orden.toIntOrNull() ?: 0,
                    ticketId = 0
                )
            )
        }
    }
}


