package com.kotlin.study.ktboard.controller.dto

import com.kotlin.study.ktboard.domain.Board

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
