package com.uefa.wordle.wordlegame.data.remote.mapper

import com.uefa.wordle.core.business.Mapper
import com.uefa.wordle.wordlegame.business.domain.model.SubmitWordResponse
import com.uefa.wordle.wordlegame.data.remote.model.SubmitWordResponseE
import com.uefa.wordle.wordlegame.presentation.LetterStatus
import javax.inject.Inject

internal class SubmitWordResponseDMapper @Inject constructor() : Mapper<SubmitWordResponseE,SubmitWordResponse>{
    override fun map(from: SubmitWordResponseE): SubmitWordResponse {
        return from.run {
            val userSubmitFlag = userSubmitflag ?: usersubmitflag ?: listOf()
            val userWord = userWord ?: userword ?: ""
            val wordState = userWord.toList().zip(userSubmitFlag).map {
                Pair(
                    first = it.first,
                    second = when(it.second){
                        0 -> LetterStatus.ABSENT
                        1 -> LetterStatus.CORRECT
                        2 -> LetterStatus.PRESENT
                        else -> LetterStatus.UNUSED
                    }
                )
            }

            SubmitWordResponse(
                attemptNo = attemptNo ?: attemptno ?: 0,
                gdId = gdId ?: gdid ?: "",
                gtFlag = gtFlag ?: 0,
                isHintuse = isHintuse ?: ishintuse ?: 0,
                mastWord = mastWord ?: mastword ?: "",
                userAttemptNo = userAttemptNo ?: 0,
                userPoint = userPoint ?: userpoint ?: 0,
                userSubmitflag = wordState,
                userWord = userWord,
                wordLength = wordLength ?: wordlength ?: 0
            )
        }
    }
}