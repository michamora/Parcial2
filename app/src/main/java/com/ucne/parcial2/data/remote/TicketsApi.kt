package com.ucne.parcial2.data.remote

import com.ucne.parcial2.data.remote.dto.TicketDto
import retrofit2.Response
import retrofit2.http.*

interface TicketsApi{
    @GET("/api/tickets")
    suspend fun getTickets(): List<TicketDto>
    @POST("/api/Tickets")
    suspend fun postTickets(@Body ticketDto: TicketDto) : TicketDto
    @PUT("/api/Tickets/{id}")
    suspend fun putTickets(@Path("id") id: Int, @Body ticketDto: TicketDto): Response<Unit>

    @DELETE("/api/Tickets/{id}")
    suspend fun deleteTickets(@Path("id") id: Int)
}