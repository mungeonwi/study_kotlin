package com.healthhub.boardwebflux.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "TB_BOARD")
data class Board (
    @Id
    var id: Long = 0L,

    var title: String? = null,

    var content: String? = null,

    var creator: String? = null,

)
