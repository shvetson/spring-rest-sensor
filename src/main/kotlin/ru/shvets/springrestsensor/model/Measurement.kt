package ru.shvets.springrestsensor.model

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 19:15
 */

@Entity
@Table(name="measurements")
class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id: Long? = null

    @field:NotEmpty(message = "Значение температуры не должно быть пустым")
    @field:Min(value = -100, message = "Температура должна быть выше -100")
    @field:Max(value = 100, message = "Температура должна быть ниже 100")
    @Column(name="value", nullable = false)
    var value: Float? = null

    @field:Pattern(regexp = "^true$|^false$", message = "Должно быть true или false")
    @Column(name="is_raining")
    var raining: Boolean? = null

    @Column(name="created_at", nullable = false)
    var createdAt: LocalDateTime? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    var sensor: Sensor? = null

    constructor()
}