package com.kotlin.study.ktboard.Exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundBoardException(msg: String) : Throwable(msg)
