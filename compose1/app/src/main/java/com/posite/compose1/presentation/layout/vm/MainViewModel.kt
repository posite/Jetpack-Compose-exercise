package com.posite.compose1.presentation.layout.vm

import androidx.compose.runtime.State
import com.posite.compose1.data.dto.test.response.TestPostResponseDto
import com.posite.compose1.data.dto.test.response.UserInfoResponseDto

interface MainViewModel {
    val count1: State<Int>
    val count2: State<Int>
    val count3: State<Int>
    val userInput1: State<String>
    val userInput2: State<String>
    val userInput1Visible: State<Boolean>
    val userInput2Visible: State<Boolean>
    val surfaceSelect: State<Boolean>
    val progressAmount: State<Float>
    val allUserInfo: State<UserInfoResponseDto>
    val post: State<TestPostResponseDto>

    fun onOneClick()
    fun onTwoClick()
    fun onThreeClick()
    fun onUser1Inputted(input: String)
    fun onUser2Inputted(input: String)
    fun onUser1InputVisibleClick()
    fun onUser2InputVisibleClick()
    fun onSurfaceClick()
    fun upProgress()
    fun downProgress()
    fun fetchAllUserInfo()
    fun fetchPost(postId: Int)
}