package com.kotlin.study.ktboard.repository

import com.kotlin.study.ktboard.domain.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long>{
    // @TODO implements
}