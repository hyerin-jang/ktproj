package com.project.ktproj.dto.board

data class BoardUpdateRequestDto(
    val id: Long,
    val writer: String,
    val password: String,
    val type: String,
    val answer: String,
) {
}