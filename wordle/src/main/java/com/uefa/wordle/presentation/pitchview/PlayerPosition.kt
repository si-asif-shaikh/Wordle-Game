package com.uefa.wordle.presentation.pitchview

data class PlayerPosition(val skill: Int, val index: Int)
data class Player(val name: String,val isOnBench:Boolean = false)