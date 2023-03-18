package com.ucne.parcial2.ui.tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TicketsUiState(
    val ticketsList: List<TicketEntity> = emptyList()
)

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    var asunto by mutableStateOf("")
    var asuntoError by mutableStateOf("")

    var empresa by mutableStateOf("")
    var empresaError by mutableStateOf("")

    var encargadoId by mutableStateOf("")
    var encargadoError by mutableStateOf("")

    var especificaciones by mutableStateOf("")
    var especificacionesError by mutableStateOf("")

    var estatus by mutableStateOf("")

    var fecha by mutableStateOf("")

    var orden by mutableStateOf("")

    var uiState = MutableStateFlow(TicketsUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            refrescarTicket()
        }
    }

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
    fun onEncargadoChanged(encargado: String) {
        this.encargadoId = encargado
        HayErrores()
    }

    private suspend fun refrescarTicket() {
        ticketRepository.getList().collect { lista ->
            uiState.update {
                it.copy(ticketsList = lista)
            }
        }
    }

    fun insertar() {

        if (HayErrores())
            return

        val ticket = TicketEntity(
            asunto = asunto,
            empresa = empresa,
            encargadoId = encargadoId.toIntOrNull() ?: 0,
            especificaciones = especificaciones,
            estatus = estatus,
            fecha = fecha,
            orden = orden.toIntOrNull() ?: 0
        )

        viewModelScope.launch(Dispatchers.IO) {
            ticketRepository.insert(ticket)
            Limpiar()
        }
    }
    fun limpiar() {

            Limpiar()
        }

    private fun HayErrores(): Boolean {
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

        encargadoError = ""
        if (encargadoId.isBlank()) {
            encargadoError = "  Debe indicar el encargado"
            hayError = true
        }


        return hayError
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
}