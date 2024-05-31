package com.healthhub.boardwebflux.controller.dto

import com.healthhub.boardwebflux.domain.Board


data class ReqCreateBoardDto(
    val title: String,
    val content: String,
    val creator: String,
)

fun ReqCreateBoardDto.toEntity() = Board(
    title = title,
    content = content,
    creator = creator,
)
