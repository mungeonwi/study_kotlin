package com.healthhub.boardwebflux.service

import com.healthhub.boardwebflux.Exception.NotFoundBoardException
import com.healthhub.boardwebflux.controller.dto.ReqCreateBoardDto
import com.healthhub.boardwebflux.controller.dto.toEntity
import com.healthhub.boardwebflux.domain.Board
import com.healthhub.boardwebflux.repository.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@Transactional(readOnly = true)
class BoardService (
    private val boardRepository: BoardRepository,
){
    @Transactional
    fun create(reqCreateBoardDto: ReqCreateBoardDto): Mono<Board> {
        return boardRepository.save(reqCreateBoardDto.toEntity())
    }

    fun findById(id: Long): Mono<Board> {
        return boardRepository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundBoardException("Not found by id: $id")))
    }
}