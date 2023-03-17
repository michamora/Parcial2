package com.ucne.parcial2.data.local.dao

import androidx.room.*
import com.ucne.parcial2.data.local.entity.TicketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticketEntity: TicketEntity)

    @Delete
    suspend fun delete(ticketEntity: TicketEntity)

    @Query(
        """
        SELECT * 
        FROM Tickets
        WHERE TicketId=:ticketId
        LIMIT 1
    """
    )
    suspend fun find(ticketId: Int): TicketEntity?

    @Query(
        """SELECT * 
        FROM Tickets
        ORDER BY ticketId desc
    """
    )
    fun getList(): Flow<List<TicketEntity>>

    @Query(
        """SELECT * 
        FROM Tickets
        WHERE enviado=0
    """
    )
    suspend fun getNoEnviadas(): List<TicketEntity>
}