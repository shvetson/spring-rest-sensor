package ru.shvets.springrestsensor.util

import jakarta.validation.ConstraintViolation
import java.util.stream.Collectors

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  10.02.2023 22:30
 */

data class ResultResponse (
    var message: String = "Запись сформирована корректно.",
    var success: Boolean = true,
    var timestamp: Long = System.currentTimeMillis()
)