package com.kotlin.study.ktboard.service

import com.kotlin.study.ktboard.Exception.NotFoundBoardException
import com.kotlin.study.ktboard.controller.dto.ReqCreateBoardDto
import com.kotlin.study.ktboard.controller.dto.ReqUpdateBoaredDto
import com.kotlin.study.ktboard.controller.dto.toEntity
import com.kotlin.study.ktboard.repository.BoardRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardServiceTest(
    @Autowired val boardService: BoardService,
    @Autowired val boardRepository: BoardRepository,
){
    @AfterEach
    fun before(){
        boardRepository.deleteAll()
    }

    @Test
    @DisplayName("게시글_생성_테스트")
    fun createTest(){
        val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
        val create = boardService.create(reqCreateBoardDto)

        assertNotNull(create.id)
        assertEquals("test1 title", create.title)
        assertEquals("test1 content", create.content)
        assertEquals("test1 creator", create.creator)

    }

    @Test
    @DisplayName("게시글_단일_조회_테스트")
    fun readTest(){
        val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
        val create = boardRepository.save(reqCreateBoardDto.toEntity())

        val get = boardService.get(create.id)

        assertEquals(create.id, get.id)
        assertEquals(create.title, get.title)
        assertEquals(create.content, get.content)
        assertEquals(create.creator, get.creator)
    }
    @Test
    @DisplayName("게시글_전체_조회_테스트")
    fun readAllTest(){
        val list = listOf(
            ReqCreateBoardDto(title = "test1 title", content = "test1 content", creator = "test1 creator"),
            ReqCreateBoardDto(title = "test2 title", content = "test2 content aaa", creator = "test2 creator"),
            ReqCreateBoardDto(title = "test3 title", content = "test3 content aaa", creator = "test3 creator"),
            ReqCreateBoardDto(title = "test4 title", content = "test4 content aaa", creator = "test4 creator"),
            ReqCreateBoardDto(title = "test5 title", content = "test5 content", creator = "test5 creator"),
        )

        list.forEach { boardRepository.save(it.toEntity()) }


        // aaa 3개 조회

        // title이 2 인거 조회

    }

    @Test
    @DisplayName("게시글_수정_테스트")
    fun updateTest(){
        val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
        val create = boardRepository.save(reqCreateBoardDto.toEntity())

        val reqUpdateBoaredDto = ReqUpdateBoaredDto(title = "update test title")
        val update = boardService.update(create.id, reqUpdateBoaredDto)


        assertEquals(create.id, update.id)
        assertEquals(reqUpdateBoaredDto.title, update.title)
        assertEquals(create.content, update.content)
        assertEquals(create.creator, update.creator)
    }

    @Test
    @DisplayName("게시글_삭제_테스트")
    fun deleteTest(){
        val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
        val create = boardRepository.save(reqCreateBoardDto.toEntity())

        boardService.delete(create.id)

        assertThrows<NotFoundBoardException>{
            boardService.get(create.id)
        }
    }
}