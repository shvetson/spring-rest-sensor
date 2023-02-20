package ru.shvets.springrestsensor.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.shvets.springrestsensor.model.Measurement
import ru.shvets.springrestsensor.repository.MeasurementRepository
import java.time.LocalDateTime

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.02.2023 09:44
 */

@Service
@Transactional(readOnly = true)
class MeasurementService(
    private val measurementRepository: MeasurementRepository
) {
    @Transactional
    fun save(measurement: Measurement) {
        enrichMeasurement(measurement)
        measurementRepository.save(measurement)
    }

    fun countRainingDay(raining: Boolean): Int {
        return measurementRepository.countAllByRainingEquals(raining)
    }

    private fun enrichMeasurement(measurement: Measurement) {
        measurement.createdAt = LocalDateTime.now()
    }
}