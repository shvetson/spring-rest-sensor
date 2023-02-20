package ru.shvets.springrestsensor.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.shvets.springrestsensor.model.Measurement

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.02.2023 09:43
 */

@Repository
interface MeasurementRepository: JpaRepository<Measurement, Long> {
    fun countAllByRainingEquals(raining: Boolean): Int
}