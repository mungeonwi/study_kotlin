package com.kotlin.study.ktboard.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "TB_BOARD")
data class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var title: String? = null,

    var content: String? = null,

    var creator: String? = null,

)
