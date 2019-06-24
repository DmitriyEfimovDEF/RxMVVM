package ru.aisdev.rxjavamvvm.rx.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Repo::class],version = 2)
abstract class AppDataBase : RoomDatabase(){

    companion object{
        var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            INSTANCE?.let {
                INSTANCE
            }?:run{
               INSTANCE = Room.databaseBuilder(context,AppDataBase::class.java,"repo.db").build()
            }
            return INSTANCE

        }
    }

    abstract fun getRepoDao(): RepoDAO

}