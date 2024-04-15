package com.posite.compose1.domain.repository.test

import com.posite.compose1.data.dto.test.response.TestPostResponseDto
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import com.posite.compose1.uitl.NetworkResult

interface TestRepository {
    suspend fun fetchAllUserInfo(): NetworkResult<UserInfoResponseDto>
    suspend fun fetchPostById(postId: Int): NetworkResult<TestPostResponseDto>
}