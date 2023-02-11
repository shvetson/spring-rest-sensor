package ru.shvets.springrestsensor.util

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import ru.shvets.springrestsensor.dto.MeasurementDTO
import ru.shvets.springrestsensor.model.Measurement
import ru.shvets.springrestsensor.service.SensorService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.02.2023 23:49
 */

@Component
class MeasurementValidator(
    private val modelMapper: ModelMapper,
    private val sensorService: SensorService
) : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return MeasurementDTO::javaClass == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val measurementDTO = target as MeasurementDTO
        val measurement: Measurement = modelMapper.map(measurementDTO, Measurement::class.java)
        val sensor = measurement.sensor

        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors,
            "value",
            "field.temp.null",
            "Должно быть введено значение температуры."
        )

        val min: Float = -100F
        val max: Float = 100F
        if (measurement.value != null) {
            if (measurement.value!! !in min..max) {
                errors.rejectValue(
                    "value",
                    "field.temp.range",
                    "Значение температуры должно быть от $min до $max включительно."
                )
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors,
            "raining",
            "field.raining.null",
            "Должен быть указан признак дождя."
        )

        val isSensor = sensorService.findByName(sensor?.name.toString()).isPresent
        if (!isSensor) {
            errors.rejectValue(
                "sensor.name",
                "field.sensor.not-exists",
                "Сенсора с таким наименованием нет."
            )
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors,
            "sensor.name",
            "field.sensor.null",
            "Должно быть указано наименование сенсора."
        )
    }
}