package com.kotlin.study.ktboard.service

import com.kotlin.study.ktboard.Exception.NotFoundBoardException
import com.kotlin.study.ktboard.controller.dto.ReqCreateBoardDto
import com.kotlin.study.ktboard.controller.dto.ReqUpdateBoaredDto
import com.kotlin.study.ktboard.controller.dto.toEntity
import com.kotlin.study.ktboard.domain.Board
import com.kotlin.study.ktboard.repository.BoardRepository
import org.hibernate.annotations.NotFound
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BoardService(
    private val boardRepository: BoardRepository,
) {
    @Transactional
    fun create(reqCreateBoardDto: ReqCreateBoardDto): Board {
        return boardRepository.save(reqCreateBoardDto.toEntity())
    }

    fun get(id: Long): Board {
        return boardRepository.findByIdOrNull(id)
            ?: throw NotFoundBoardException("Not found by id: $id")
    }

    @Transactional
    fun update(
        id: Long,
        reqUpdateBoaredDto: ReqUpdateBoaredDto,
    ) : Board{
        val board = this.get(id).apply {
            if(!reqUpdateBoaredDto.title.isNullOrEmpty()) title = reqUpdateBoaredDto.title
            if(!reqUpdateBoaredDto.content.isNullOrEmpty()) content = reqUpdateBoaredDto.content
        }
        return boardRepository.save(board)
    }

    @Transactional
    fun delete(id: Long) {
        val get = this.get(id)
//        boardRepository.deleteById(id)
        boardRepository.delete(get)
    }
}