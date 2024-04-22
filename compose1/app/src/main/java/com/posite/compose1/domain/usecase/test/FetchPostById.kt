package com.posite.compose1.domain.usecase.test

import android.util.Log
import com.posite.compose1.data.dto.test.response.TestPostResponseDto
import com.posite.compose1.domain.repository.test.TestRepository
import com.posite.compose1.uitl.NetworkResult
import com.posite.compose1.uitl.onError
import com.posite.compose1.uitl.onSuccess
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchPostById @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke(postId: Int) = flow<NetworkResult<TestPostResponseDto>> {
        try {
            emit(repository.fetchPostById(postId).onSuccess {
                Log.d("FetchAllUserUseCase", "invoke: $it")
            }.onError {

            })
        } catch (e: Exception) {

        }
    }
}