package com.posite.compose1.data.datasource.test

import com.posite.compose1.data.dto.test.response.TestPostResponseDto
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import com.posite.compose1.data.service.test.TestService
import com.posite.compose1.uitl.NetworkResult
import com.posite.compose1.uitl.handleApi
import javax.inject.Inject

class TestDataSource @Inject constructor(private val service: TestService) {


    suspend fun fetchAllUserInfo(): NetworkResult<UserInfoResponseDto> {
        return handleApi({ service.fetchAllUserInfo() }) { it }
    }

    suspend fun fetchPostById(postId: Int): NetworkResult<TestPostResponseDto> {
        return handleApi({ service.fetchPostById(postId) }) { it }
    }
}