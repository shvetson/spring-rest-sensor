package ru.shvets.springrestsensor.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
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
    @JsonManagedReference
    var measurements: List<Measurement>? = null

    constructor()
}