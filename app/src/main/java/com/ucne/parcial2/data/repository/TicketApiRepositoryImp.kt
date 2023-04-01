package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class TicketApiRepositoryImp @Inject constructor(
    private val ticketsApi: TicketsApi
): TicketApiRepository {

    override fun getTickets():Flow<Resource<List<TicketDto>>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = ticketsApi.getTickets()

            emit (Resource.Success(tickets))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override suspend fun putTicket(id: Int, ticketDto: TicketDto){
        ticketsApi.putTicket(id, ticketDto)
    }
    override  fun getTicketbyId(id: Int) :Flow<Resource<TicketDto>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = ticketsApi.getTicketsbyId(id)

            emit (Resource.Success(tickets))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verifica tu conexion a internet"))
        }
    }

    override suspend fun deleteTicket(id: Int){
        ticketsApi.deleteTicket(id)
    }
    override suspend fun postTicket(ticketDto: TicketDto) {
        ticketsApi.postTicket(ticketDto)
    }
}