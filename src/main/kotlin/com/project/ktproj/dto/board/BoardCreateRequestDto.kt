package com.project.ktproj.dto.board

import com.project.ktproj.domain.question.QuestionType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class BoardCreateRequestDto(

    @field:NotBlank(message = "이름은 필수입니다.")
    val writer: String?,

    @field:NotBlank(message = "비밀번호는 필수입니다.")
    @field:Size(min = 4, max = 10)
    val password: String?,

    val type: QuestionType?,

    val answer: String?,
) {

}