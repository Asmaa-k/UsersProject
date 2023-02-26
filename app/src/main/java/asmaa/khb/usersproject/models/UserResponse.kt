package asmaa.khb.usersproject.models

import com.google.gson.annotations.SerializedName

data class UserResponse constructor(
    @SerializedName("id") val ID: Long,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val emailAddress: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("address") val address: Address
)

data class Address(@SerializedName("city") val cityName: String)