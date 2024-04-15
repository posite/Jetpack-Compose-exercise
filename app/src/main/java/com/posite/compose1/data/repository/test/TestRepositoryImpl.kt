package com.posite.compose1.data.repository.test

import com.posite.compose1.data.datasource.test.TestDataSource
import com.posite.compose1.data.dto.test.response.TestPostResponseDto
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import com.posite.compose1.domain.repository.test.TestRepository
import com.posite.compose1.uitl.NetworkResult
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testDataSource: TestDataSource
) : TestRepository {
    override suspend fun fetchAllUserInfo(): NetworkResult<UserInfoResponseDto> =
        testDataSource.fetchAllUserInfo()

    override suspend fun fetchPostById(postId: Int): NetworkResult<TestPostResponseDto> =
        testDataSource.fetchPostById(postId)

}