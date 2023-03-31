package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.local.entity.toTicketDto
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import kotlinx.coroutines.flow.Flow
import com.ucne.parcial2.data.local.dao.TicketDao
import javax.inject.Inject

class TicketRepository @Inject constructor
    (
    private val ticketsApi: TicketsApi,
    private val ticketDao: TicketDao
) {
    suspend fun insert(ticket: TicketEntity){

        ticketsApi.postTicket(ticket.toTicketDto())
    }

    suspend fun delete(ticket: TicketEntity){
        ticketDao.delete(ticket)
        ticketsApi.deleteTicket(ticket.ticketId!!)
    }

    suspend fun update(ticket: TicketEntity){
        ticketDao.update(ticket)
    }
    suspend fun find(ticketId:Int) = ticketDao.find(ticketId)
    suspend fun putTickets(id: Int, ticketDto: TicketDto) = ticketsApi.putTicket(id, ticketDto)
    suspend fun postTickets(ticketDto: TicketDto) = ticketsApi.postTicket(ticketDto)

    fun getList(): Flow<List<TicketEntity>> = ticketDao.getList()


}