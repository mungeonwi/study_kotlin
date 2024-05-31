package com.healthhub.boardwebflux.repository

import com.healthhub.boardwebflux.domain.Board
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : R2dbcRepository<Board, Long>{
}