package ru.shvets.springrestsensor.dto

import ru.shvets.springrestsensor.model.Sensor

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.02.2023 09:51
 */

data class MeasurementDTO (
    var value: Float? = null,
    var raining: Boolean? = null,
    var sensor: Sensor? = null
)