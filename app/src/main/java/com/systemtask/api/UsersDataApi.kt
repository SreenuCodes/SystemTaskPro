package com.systemtask.api

import com.systemtask.model.UserDetailsItem
import retrofit2.Response
import retrofit2.http.GET

interface UsersDataApi {
    @GET("v2/users")
    suspend fun getUsersDetails(): Response<List<UserDetailsItem>>
}