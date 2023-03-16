package com.ucne.parcial2.data.remote

import com.ucne.parcial2.data.remote.dto.TicketDto
import okhttp3.Response
import retrofit2.http.*


interface TicketsApi{
    @GET("/api/ticket")
    suspend fun getTickets(): List<TicketDto>

    @POST("/api/Tickets")
    suspend fun postTickets(ticketDto: TicketDto)
    @PUT("/api/Tickets/{id}")
    suspend fun putTickets(@Path("id") id: Int, @Body ticketDto: TicketDto): retrofit2.Response<Unit>
    @DELETE("/api/Tickets/{id}")
    suspend fun deleteTickets(@Path("id") id: Int)
}