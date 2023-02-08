package ru.shvets.springrestsensor.dto

import jakarta.persistence.Column
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 22:30
 */

class SensorDTO(
    @field:NotEmpty(message = "Имя не должно быть пустым")
    @field:Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    @Column(name = "name", nullable = false)
    var name: String? = null
)