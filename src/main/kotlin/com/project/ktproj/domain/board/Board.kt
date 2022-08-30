package com.project.ktproj.domain.board

import com.project.ktproj.domain.base.BaseTimeEntity
import com.project.ktproj.domain.question.QuestionType
import javax.persistence.*

@Entity
class Board(

    var writer: String?,

    val password: String?,

    @Enumerated(EnumType.STRING)
    val type: QuestionType?,

    var answer: String?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,
) : BaseTimeEntity() {
    fun updateBoard(answer: String) {
        this.answer = answer
    }
}