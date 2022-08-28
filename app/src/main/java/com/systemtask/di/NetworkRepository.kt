package com.systemtask.di

import com.systemtask.api.UsersDataApi
import com.systemtask.model.UserDetailsItem
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val usersDataApi: UsersDataApi
) {

    suspend fun getUsersList(): Response<List<UserDetailsItem>> {
        return usersDataApi.getUsersDetails()
    }

}