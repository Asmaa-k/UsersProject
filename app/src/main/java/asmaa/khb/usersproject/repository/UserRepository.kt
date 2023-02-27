package asmaa.khb.usersproject.repository

import androidx.room.withTransaction
import asmaa.khb.usersproject.api.UserApi
import asmaa.khb.usersproject.api.util.networkBoundResource
import asmaa.khb.usersproject.models.User
import asmaa.khb.usersproject.models.UserResponse
import asmaa.khb.usersproject.room.UserDatabase
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi, private val db: UserDatabase
) {
    private val restaurantDao = db.userDao()

    fun getUsersList() = networkBoundResource(query = {
        restaurantDao.getAllUsers()
    }, fetch = {
        delay(2000)
        api.getUsersList()
    }, map = {
        mapResult(it)
    }, saveFetchResult = { users ->
        db.withTransaction {
            restaurantDao.deleteAllUsers()
            restaurantDao.insertListOfUsers(users)
        }
    })

    private fun mapResult(responseUserList: List<UserResponse>): List<User> {
        val userList = mutableListOf<User>()
        for (user in responseUserList) {
            userList.add(
                User(
                    user.ID,
                    user.firstName,
                    user.lastName,
                    user.emailAddress,
                    user.avatar,
                    user.address.cityName
                )
            )
        }
        return userList
    }
}