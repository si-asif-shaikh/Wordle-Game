package com.uefa.wordle.core.data.remote.buster

import com.uefa.wordle.core.business.utils.Logger
import java.util.UUID

object BusterData {

    private var mixedBuster = ""
    private var gameBuster = ""


    fun getBusterString(getEndpointPath: GetEndpointPath): String? {
        return when (getEndpointPath) {
            GetEndpointPath.FEED -> mixedBuster
            GetEndpointPath.GAME_API -> gameBuster
        }
    }

    fun refreshBuster(slug: GetEndpointPath): String {
        val busterString = getBuster()
        setBusterString(slug, busterString)
        return busterString
    }

    fun refreshAllBuster() {
        GetEndpointPath.entries.forEach { refreshBuster(it) }
    }


    private fun getBuster(): String =
        UUID.randomUUID().toString() + System.currentTimeMillis().toString()

    private fun setBusterString(getEndpointPath: GetEndpointPath, busterString: String) {
        Logger.d(
            "REFRESH_TAG",
            "Updating buster - Path - ${getEndpointPath.path} & Buster - $busterString"
        )
        when (getEndpointPath) {
            GetEndpointPath.FEED -> {
                mixedBuster = busterString
            }

            GetEndpointPath.GAME_API -> {
                gameBuster = busterString
            }
        }
    }

}