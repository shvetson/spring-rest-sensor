package ru.shvets.springrestsensor.controller

import jakarta.validation.*
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import ru.shvets.springrestsensor.dto.MeasurementDTO
import ru.shvets.springrestsensor.exception.MeasurementNotCreateException
import ru.shvets.springrestsensor.exception.SensorNotFoundException
import ru.shvets.springrestsensor.model.Measurement
import ru.shvets.springrestsensor.model.Sensor
import ru.shvets.springrestsensor.service.MeasurementService
import ru.shvets.springrestsensor.service.SensorService
import ru.shvets.springrestsensor.util.MeasurementValidator
import ru.shvets.springrestsensor.util.ResultResponse
import java.util.*
import java.util.stream.Collectors

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.02.2023 09:47
 */

@RestController
@RequestMapping("/measurements")
class MeasurementController(
    private val modelMapper: ModelMapper,
    private val measurementService: MeasurementService,
    private val sensorService: SensorService,
    private val measurementValidator: MeasurementValidator
) {

    @GetMapping
    fun getAllMeasurements(): List<Sensor> {
        return sensorService.getAll()
    }

    @PostMapping("/add")
    fun addMeasurement(
        @RequestBody @Valid measurementDTO: MeasurementDTO,
        bindingResult: BindingResult
    ): ResponseEntity<ResultResponse> {

        measurementValidator.validate(measurementDTO, bindingResult)
        if (bindingResult.hasErrors()) {
            val message = bindingResult.fieldErrors.stream().map {
                it.field + " - " + it.defaultMessage
            }.collect(Collectors.joining(", "))
            throw MeasurementNotCreateException(message)
        }

        val sensor = sensorService.findByName(measurementDTO.sensor?.name.toString()).get()

        val measurement = modelMapper.map(measurementDTO, Measurement::class.java)
        measurement.apply { this.sensor = sensor }

        measurementService.save(measurement)
        return ResponseEntity(ResultResponse(), HttpStatus.OK)
    }

    @GetMapping("/rainyDaysCount")
    fun countRainyDays(): String {
        val countDays = measurementService.countRainingDay(true)
        return "Количество дождливых дней - $countDays"
    }

    @ExceptionHandler
    fun handlerException(e: RuntimeException): ResponseEntity<ResultResponse> {
        val resultResponse = ResultResponse()
        resultResponse.apply {
            this.message = e.message.toString()
            this.success = false
            this.timestamp = System.currentTimeMillis()
        }

        val status: HttpStatus = when (e) {
            is MeasurementNotCreateException -> HttpStatus.BAD_REQUEST
            is SensorNotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.BAD_REQUEST
        }

        return ResponseEntity(resultResponse, status)
    }
}