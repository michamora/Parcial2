package com.ucne.parcial2.data.remote

import com.ucne.parcial2.data.remote.dto.TicketDto
import retrofit2.Response
import retrofit2.http.*

interface TicketsApi{
    @GET("/api/tickets")
    suspend fun getTickets(): List<TicketDto>
    @GET("/api/tickets/{id}")
    suspend fun getTicketsbyId(@Path("id") id: Int):TicketDto
    @POST("/api/Tickets")
    suspend fun postTicket(ticketsDto: TicketDto)
    @PUT("/api/Tickets/{id}")
    suspend fun putTicket(@Path("id") id: Int, @Body ticketsDto: TicketDto):Response<Unit>
    @DELETE("/api/Tickets/{id}")
    suspend fun deleteTicket(@Path("id") id: Int)
}