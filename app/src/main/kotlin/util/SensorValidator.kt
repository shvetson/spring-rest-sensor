package ru.shvets.springrestsensor.util

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import ru.shvets.springrestsensor.dto.SensorDTO
import ru.shvets.springrestsensor.model.Sensor
import ru.shvets.springrestsensor.service.SensorService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  07.02.2023 17:17
 */

@Component
class SensorValidator(
    private val sensorService: SensorService,
    private val modelMapper: ModelMapper,
) : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return SensorDTO::javaClass == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val sensorDTO = target as SensorDTO
        val sensor: Sensor = modelMapper.map(sensorDTO, Sensor::class.java)

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
            "name",
            "field.name.null",
            "Наименование сенсора не должно быть пустым.")

        val min: Int = 3
        val max: Int = 30
        if (sensorDTO.name?.length!! !in min..max) {
            errors.rejectValue(
                "name",
                "field.name.range",
                "Длина наименования сенсора должно быть от $min до $max символов."
            )
        }

        if (sensorService.findByName(sensor.name!!).isPresent) {
            errors.rejectValue(
                "name",
                "field.name.exists",
                "Сенсор с таким наименованием уже существует."
            )
        }
    }
}