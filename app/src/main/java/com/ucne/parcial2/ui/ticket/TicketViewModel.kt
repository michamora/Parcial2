package com.ucne.parcial2.ui.ticket


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
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OcupacionUiState(
    val ticketList: List<TicketEntity> = emptyList()
)

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    var asunto by mutableStateOf("")
    var empresa by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var estatus by mutableStateOf("")
    var fecha by mutableStateOf("")
    var orden by mutableStateOf("")
}
/*

    var uiState = MutableStateFlow(TicketsUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {


        }
    }


    fun insertar() {
        val ticket = TicketEntity(
            asunto = asunto,
            empresa = empresa,
            especificaciones = especificaciones,
            estatus = estatus,
            fecha = fecha,
            orden = orden
        )

        viewModelScope.launch(Dispatchers.IO) {
            ticketRepository.insert(ticket)
            Limpiar()
        }
    }

    private fun Limpiar() {
        asunto = ""
        empresa = ""
        especificaciones = ""
        estatus = ""
        fecha = ""
        orden = ""
    }

}*/