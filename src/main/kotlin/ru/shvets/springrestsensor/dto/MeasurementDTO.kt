package ru.shvets.springrestsensor.dto

import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range
import ru.shvets.springrestsensor.model.Sensor

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.02.2023 09:51
 */

data class MeasurementDTO (

//    @field:Min(value = -100, message = "{measurement.temp.min}")
//    @field:Max(value = 100, message = "{measurement.temp.max}")
//    @field:NotNull(message = "{measurement.value.null}")
//    @field:Range(min = -100, max = 100, message = "{measurement.temp.range}")
    var value: Float? = null,

//    @field:NotNull(message = "{measurement.value.null}")
    var raining: Boolean? = null,

//    @field:NotNull(message = "{measurement.value.null}")
    var sensor: Sensor? = null
)