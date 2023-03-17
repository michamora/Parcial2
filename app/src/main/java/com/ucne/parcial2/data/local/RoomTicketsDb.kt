package com.ucne.parcial2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.parcial2.data.local.dao.TicketDao
import com.ucne.parcial2.data.local.entity.TicketEntity

@Database(
    entities = [
        TicketEntity::class
    ],
    version = 1
)
abstract class RoomTicketsDb : RoomDatabase() {
    abstract val ticketDao: TicketDao
}