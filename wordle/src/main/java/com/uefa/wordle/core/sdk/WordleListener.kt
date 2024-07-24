package com.uefa.wordle.core.sdk

/**
 * Interface for communication between Game and the host app
 * Implement it ad pass it to the [Wordle.listener]
 *
 * Note:
 * Methods marked as Required *should* be fully implemented.
 * Methods marked as Optional may be not implemented by the host (no-op)
 */
interface WordleListener {

    /**
     * Required.
     * It is used by the games to start login flow.
     */
    fun openLoginPage(gameId: String)

    /**
     * Optional.
     * It is used to ask to close game.
     */
    fun closeGame()

    fun clearAppToken()

    fun openRegistrationPage()

    /**
     * Required.
     * This suspend function is used by the SDK to request for refresh Volt token from client module.
     * @return Refresh Volt token that will be used by SDK to update its session token.
     */
    suspend fun onRequestRefreshToken(): String?

    /**
     * Optional.
     * This function is called by the SDK to request for redirection to a page with the URI path.
     */
    fun openUri(uriPath: String)
}