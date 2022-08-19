package com.project.ktproj.service.question

import com.project.ktproj.domain.question.Question
import com.project.ktproj.domain.question.QuestionRepository
import com.project.ktproj.domain.question.QuestionType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionService(
    private val questionRepository: QuestionRepository
) {
    fun getQuestions(type: QuestionType): List<Question> {
        return questionRepository.findByType(type)
    }

}