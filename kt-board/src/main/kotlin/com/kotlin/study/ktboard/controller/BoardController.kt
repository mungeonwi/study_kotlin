package com.kotlin.study.ktboard.controller

import com.kotlin.study.ktboard.controller.dto.ReqCreateBoardDto
import com.kotlin.study.ktboard.domain.Board
import com.kotlin.study.ktboard.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService : BoardService,
) {
    // @TODO implements
    @PostMapping
    fun create(
        @RequestBody dto: ReqCreateBoardDto,
    ): ResponseEntity<Board> {
        return ResponseEntity.ok(boardService.create(dto))
    }


}