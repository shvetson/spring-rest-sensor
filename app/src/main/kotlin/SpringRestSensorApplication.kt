package ru.shvets.springrestsensor

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringRestSensorApplication{

    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }
}

fun main(args: Array<String>) {
    runApplication<SpringRestSensorApplication>(*args)
}
