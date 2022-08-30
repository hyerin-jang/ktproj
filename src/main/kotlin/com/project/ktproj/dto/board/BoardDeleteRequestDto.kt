package com.project.ktproj.dto.board

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class BoardDeleteRequestDto(
    val writer: String,

    @field:NotBlank(message = "비밀번호는 필수입니다.")
    @field:Size(min = 4, max = 10)
    val password: String,
) {
}