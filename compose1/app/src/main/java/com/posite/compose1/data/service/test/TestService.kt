package com.posite.compose1.data.service.test

import com.posite.compose1.data.dto.test.response.TestPostResponseDto
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TestService {
    @GET("users")
    suspend fun fetchAllUserInfo(): Response<UserInfoResponseDto>

    @GET("posts/{postId}")
    suspend fun fetchPostById(@Path("postId") postId: Int): Response<TestPostResponseDto>
}