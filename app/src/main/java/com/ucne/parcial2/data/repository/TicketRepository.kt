package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.local.entity.toTicketDto
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import javax.inject.Inject

class TicketRepository @Inject constructor
    (
    private val ticketsApi: TicketsApi
) {
    suspend fun insert(ticket: TicketEntity){

        ticketsApi.postticket(ticket.toTicketDto())
    }

    suspend fun putTicket(id: Int, ticketDto: TicketDto) = ticketsApi.putTicket(id, ticketDto)


}