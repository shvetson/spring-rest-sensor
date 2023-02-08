package ru.shvets.springrestsensor.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  08.02.2023 18:57
 */

@Entity
@Table(name = "sensors")
class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_who")
    var createdWho: String? = null

    @OneToMany(mappedBy = "sensor")
    var measurements: List<Measurement>? = null

    constructor()

    override fun toString(): String {
        return "Sensor(id=$id, name=$name)"
    }
}