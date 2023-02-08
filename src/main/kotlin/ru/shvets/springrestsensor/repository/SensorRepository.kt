package ru.shvets.springrestsensor.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.shvets.springrestsensor.model.Sensor
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 21:52
 */

@Repository
interface SensorRepository: JpaRepository<Sensor, Long> {
    fun findByName(name: String): Optional<Sensor>
}