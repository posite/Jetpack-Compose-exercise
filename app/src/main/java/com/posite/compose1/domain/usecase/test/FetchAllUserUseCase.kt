package com.posite.compose1.domain.usecase.test

import android.util.Log
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import com.posite.compose1.domain.repository.test.TestRepository
import com.posite.compose1.uitl.NetworkResult
import com.posite.compose1.uitl.onError
import com.posite.compose1.uitl.onSuccess
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAllUserUseCase @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke() = flow<NetworkResult<UserInfoResponseDto>> {
        try {
            emit(repository.fetchAllUserInfo().onSuccess {
                Log.d("FetchAllUserUseCase", "invoke: $it")
            }.onError {

            })
        } catch (e: Exception) {

        }
    }
}