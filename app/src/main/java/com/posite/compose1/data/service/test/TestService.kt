package com.posite.compose1.data.service.test

import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface TestService {
    @GET("users")
    suspend fun fetchAllUserInfo(): Response<UserInfoResponseDto>
}