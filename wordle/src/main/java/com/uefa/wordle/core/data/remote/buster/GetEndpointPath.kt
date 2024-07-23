package com.uefa.wordle.core.data.remote.buster

enum class GetEndpointPath(val path: String) {
    FEED("/feeds"),
    GAME_API("/wordle/services/api/gameplay"),
}