package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow

interface TicketApiRepository {
    fun getTicket(): Flow<Resource<List<TicketDto>>>
    suspend fun putTickets(id: Int, ticketDto: TicketDto)

    suspend fun deleteTickets(id: Int)
}