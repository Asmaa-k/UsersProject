package asmaa.khb.usersproject.api

import asmaa.khb.usersproject.models.UserResponse
import retrofit2.http.GET

const val BASE_URL = "https://random-data-api.com/api/v2/"
fun interface UserApi {

    @GET("users?size=20")
    suspend fun getUsersList(): List<UserResponse>
}