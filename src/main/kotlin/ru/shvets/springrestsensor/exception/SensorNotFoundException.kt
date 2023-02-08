package ru.shvets.springrestsensor.exception

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 22:48
 */

class SensorNotFoundException(id: Long): RuntimeException("Сенсор с id=${id} не найдет в базе данных")