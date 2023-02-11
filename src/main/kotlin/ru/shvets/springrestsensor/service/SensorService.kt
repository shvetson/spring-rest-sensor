package ru.shvets.springrestsensor.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.shvets.springrestsensor.exception.SensorNotFoundException
import ru.shvets.springrestsensor.model.Sensor
import ru.shvets.springrestsensor.repository.SensorRepository
import java.time.LocalDateTime
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 21:53
 */

@Service
@Transactional(readOnly = true)
class SensorService(
    private val sensorRepository: SensorRepository
) {

    fun getAll(): List<Sensor> {
        return sensorRepository.findAll()
    }

    fun findOne(id: Long): Sensor {
        val sensor: Optional<Sensor> = sensorRepository.findById(id)
        return sensor.orElseThrow { SensorNotFoundException() }
    }

    fun findByName(name: String): Optional<Sensor> {
        return sensorRepository.findByName(name)
    }

    @Transactional
    fun save(sensor: Sensor) {
        enrichSensor(sensor)
        sensorRepository.save(sensor)
    }

    private fun enrichSensor(sensor: Sensor) {
        sensor.createdAt = LocalDateTime.now()
        sensor.updatedAt = LocalDateTime.now()
        sensor.createdWho = "ADMIN"
    }
}