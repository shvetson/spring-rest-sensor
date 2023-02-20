package ru.shvets.springrestsensor.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
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

    @Column(name="value", nullable = false)
    var value: Float? = null

    @Column(name="is_raining")
    var raining: Boolean? = null

    @Column(name="created_at", nullable = false)
    var createdAt: LocalDateTime? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    @JsonBackReference
    var sensor: Sensor? = null

    constructor()
}