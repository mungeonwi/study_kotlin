package com.healthhub.boardwebflux.service

import com.healthhub.boardwebflux.Exception.NotFoundBoardException
import com.healthhub.boardwebflux.controller.dto.ReqCreateBoardDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import reactor.test.StepVerifier

@Profile("Test")
@SpringBootTest
class BoardServiceTest(
    @Autowired private val boardService: BoardService){
    @Test
    @DisplayName("게시글 생성 테스트")
    fun createTest(){
        val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
//        val create = boardService.create(reqCreateBoardDto).block()!!
        val create = boardService.create(reqCreateBoardDto)
        StepVerifier.create(create)
            .consumeNextWith{
                test ->
                assertNotNull(test.id)
                assertEquals("test1 title", test.title)
                assertEquals("test1 content", test.content)
                assertEquals("test1 creator", test.creator)
            }
            .verifyComplete()
    }
    @Nested
    @DisplayName("게시글_단일_조회_테스트")
    inner class ReadTest{
        @Test
        @DisplayName("조회_성공")
        fun readTest(){
            val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
            val board = boardService.create(reqCreateBoardDto).block()!!

            val resultMono = boardService.findById(board.id)

            StepVerifier.create(resultMono)
                .consumeNextWith{ test ->
                    assertEquals(board.id, test.id)
                    assertEquals(board.title, test.title)
                    assertEquals(board.content, test.content)
                    assertEquals(board.creator, test.creator)
            }
            .verifyComplete()
        }

        @Test
        @DisplayName("조회_실패")
        fun readFailureTest(){
            val reqCreateBoardDto = ReqCreateBoardDto("test1 title", "test1 content", "test1 creator")
            val board = boardService.create(reqCreateBoardDto).block()!!

            val resultMono = boardService.findById(342424)

            StepVerifier.create(resultMono)
                .expectError(NotFoundBoardException::class.java)
                .verify()
        }
    }
}