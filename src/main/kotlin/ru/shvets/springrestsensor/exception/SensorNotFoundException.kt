package ru.shvets.springrestsensor.exception

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 22:48
 */

class SensorNotFoundException: RuntimeException("Сенсор не найден в базе данных")