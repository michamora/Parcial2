package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.local.dao.TicketDao
import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.local.entity.toTicketsDto
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepository @Inject constructor
    (
    private val ticketDao: TicketDao,
    private val ticketsApi: TicketsApi
) {
    suspend fun insert(ticket: TicketEntity) {

        ticketDao.insert(ticket) //insertar en la base de datos

//        val ticketNoEnviadas = ticketDao.getNoEnviadas() //buscar no enviados

        ticketsApi.postTickets(ticket.toTicketsDto())

        ticketsApi.putTickets(ticket.ticketId!! ,ticket.toTicketsDto())


//        ticketNoEnviadas.map { ticketEntity ->
//            val dto = ticketEntity.toTicketDto()
//            ticketsApi.postTickets(dto)
//        }
    }

    suspend fun delete(ticket: TicketEntity) = ticketDao.delete(ticket)

    suspend fun find(ticketId:Int) = ticketDao.find(ticketId)
    suspend fun putTickets(id: Int, ticketDto: TicketDto) = ticketsApi.putTickets(id,ticketDto)

    fun getList(): Flow<List<TicketEntity>> = ticketDao.getList()
}