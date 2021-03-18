package com.example.dolarcambio.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dolarcambio.data.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransDatabase : RoomDatabase() {

    abstract fun transDao(): TransDao

    companion object {

        @Volatile
        private var INSTANCE: TransDatabase? = null

        fun getInstance(context: Context): TransDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            } else
                synchronized(this){
                    val builder = Room.databaseBuilder(context.applicationContext, TransDatabase::class.java, "transaction_table").build()
                    INSTANCE = builder
                    return builder
                }
        }
    }
}