package com.project.ktproj.domain.board

import com.project.ktproj.domain.base.BaseTimeEntity
import com.project.ktproj.domain.question.QuestionType
import javax.persistence.*

@Entity
class Board(

    val writer: String,

    val type: QuestionType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,
) : BaseTimeEntity() {
}