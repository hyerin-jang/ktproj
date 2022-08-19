package com.project.ktproj.domain.board

import com.project.ktproj.domain.base.BaseTimeEntity
import com.project.ktproj.domain.question.Question
import com.project.ktproj.domain.question.QuestionType
import javax.persistence.*

@Entity
class Board(

    var writer: String,

    @Enumerated(EnumType.STRING)
    val type: QuestionType = QuestionType.WORKER,

    @OneToMany(mappedBy = "board")
    var question: MutableList<Question> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,
) : BaseTimeEntity() {
    fun updateBoard(question: MutableList<Question>) {
        this.question = question
    }
}