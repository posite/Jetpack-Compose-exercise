package com.posite.compose1.presentation.layout.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto
import com.posite.compose1.domain.usecase.test.FetchAllUserUseCase
import com.posite.compose1.uitl.onError
import com.posite.compose1.uitl.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val fetchAllUserUseCase: FetchAllUserUseCase) :
    ViewModel(), MainViewModel {
    private val _count1 = mutableStateOf(0)
    override val count1: State<Int>
        get() = _count1

    private val _count2 = mutableStateOf(0)
    override val count2: State<Int>
        get() = _count2

    private val _count3 = mutableStateOf(0)
    override val count3: State<Int>
        get() = _count3

    private val _userInput1 = mutableStateOf("")
    override val userInput1: State<String>
        get() = _userInput1

    private val _userInput2 = mutableStateOf("")
    override val userInput2: State<String>
        get() = _userInput2

    private val _userInput1Visible = mutableStateOf(true)
    override val userInput1Visible: State<Boolean>
        get() = _userInput1Visible


    private val _userInput2Visible = mutableStateOf(true)
    override val userInput2Visible: State<Boolean>
        get() = _userInput2Visible

    private val _surfaceSelect = mutableStateOf(false)
    override val surfaceSelect: State<Boolean>
        get() = _surfaceSelect

    private val _progressAmount = mutableFloatStateOf(0.0f)
    override val progressAmount: State<Float>
        get() = _progressAmount

    private val _allUserInfo = mutableStateOf(UserInfoResponseDto())
    override val allUserInfo: State<UserInfoResponseDto>
        get() = _allUserInfo

    override fun onOneClick() {
        _count1.value = _count1.value + 1
    }

    override fun onTwoClick() {
        _count2.value = _count2.value + 2
    }

    override fun onThreeClick() {
        _count3.value = _count3.value + 3
    }

    override fun onUser1Inputted(input: String) {
        _userInput1.value = input
        Log.d("user1", _userInput1.value)
    }

    override fun onUser2Inputted(input: String) {
        _userInput2.value = input
        Log.d("user2", _userInput2.value)
    }

    override fun onUser1InputVisibleClick() {
        _userInput1Visible.value = _userInput1Visible.value.not()
    }

    override fun onUser2InputVisibleClick() {
        _userInput2Visible.value = _userInput2Visible.value.not()
    }

    override fun onSurfaceClick() {
        _surfaceSelect.value = _surfaceSelect.value.not()
    }

    override fun upProgress() {
        _progressAmount.floatValue = _progressAmount.floatValue + 0.1f
    }

    override fun downProgress() {
        _progressAmount.floatValue = _progressAmount.floatValue - 0.1f
    }

    override fun fetchAllUserInfo() {
        viewModelScope.launch {
            fetchAllUserUseCase().collect { result ->
                result.onSuccess { userInfo ->
                    Log.d("FetchAllUser", "success: $userInfo")
                    _allUserInfo.value = userInfo
                }.onError {
                    Log.d("FetchAllUser", "error: $it")
                }
            }
        }
    }


}