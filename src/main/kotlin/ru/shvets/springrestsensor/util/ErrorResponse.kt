package ru.shvets.springrestsensor.util

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 13:35
 */

data class ErrorResponse(
    var message: String? = null,
    var timestamp: Long? = null
)