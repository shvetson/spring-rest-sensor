package ru.shvets.springrestsensor.util

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
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
): Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return SensorDTO::javaClass == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val sensorDTO = target as SensorDTO
        val sensor: Sensor = modelMapper.map(sensorDTO, Sensor::class.java)
        if (sensorService.findByName(sensor.name!!).isPresent) {
            errors.rejectValue("name", "Сенсор с таким наименованием уже существует")
        }
    }
}