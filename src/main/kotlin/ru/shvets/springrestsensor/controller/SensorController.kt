package ru.shvets.springrestsensor.controller

import jakarta.validation.Valid
import jakarta.validation.Validator
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import ru.shvets.springrestsensor.dto.SensorDTO
import ru.shvets.springrestsensor.exception.SensorNotCreateException
import ru.shvets.springrestsensor.exception.SensorNotFoundException
import ru.shvets.springrestsensor.model.Sensor
import ru.shvets.springrestsensor.service.SensorService
import ru.shvets.springrestsensor.util.ErrorResponse
import ru.shvets.springrestsensor.util.SensorValidator

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 22:28
 */

@RestController
@RequestMapping("/sensors")
class SensorController(
    private val sensorService: SensorService,
    private val modelMapper: ModelMapper,
    private val sensorValidator: SensorValidator
) {

    @GetMapping("/{id}")
    fun getSensor(@PathVariable("id") id: Long): SensorDTO {
        val sensor = sensorService.findOne(id)
        return modelMapper.map(sensor, SensorDTO::class.java)
    }

    @PostMapping("/registration")
    fun signIn(
        @RequestBody @Valid sensorDTO: SensorDTO,
        bindingResult: BindingResult
    ): ResponseEntity<HttpStatus> {
        sensorValidator.validate(sensorDTO, bindingResult)

        if (bindingResult.hasErrors()) {
            val errorMessage = StringBuilder()
            val errors: List<FieldError> = bindingResult.fieldErrors

            for (error in errors) {
                errorMessage.append(error.field)
                    .append("-").append(error.defaultMessage)
                    .append(";")
            }
            throw SensorNotCreateException(errorMessage.toString())
        }

        val sensor = modelMapper.map(sensorDTO, Sensor::class.java)
        sensorService.save(sensor)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    @ExceptionHandler
    fun handlerException(e: RuntimeException): ResponseEntity<ErrorResponse>{
        val response = ErrorResponse()
        response.apply {
            this.message = e.message.toString()
            this.timestamp = System.currentTimeMillis()
        }

        val status: HttpStatus = when (e) {
            is SensorNotCreateException -> HttpStatus.BAD_REQUEST
            is SensorNotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.NOT_FOUND
        }

        return ResponseEntity(response, status)
    }
}