package asmaa.khb.usersproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import asmaa.khb.usersproject.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}