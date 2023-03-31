package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow

interface TicketApiRepository
{
    fun getTickets(): Flow<Resource<List<TicketDto>>>
    suspend fun putTicket(id: Int, ticketsDto: TicketDto)
    suspend fun postTickets(ticketDto: TicketDto)
    fun getTicketbyId(id: Int): Flow<Resource<TicketDto>>
    suspend fun deleteTicket(id: Int)


}