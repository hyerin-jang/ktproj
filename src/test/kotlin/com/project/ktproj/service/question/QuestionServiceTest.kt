package com.project.ktproj.service.question

import com.project.ktproj.domain.question.QuestionRepository
import com.project.ktproj.domain.question.QuestionType
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class QuestionServiceTest @Autowired constructor(
    val questionService: QuestionService,
    val questionRepository: QuestionRepository,
){

    @Test
    fun getQuestions() {
        //given
        val type: QuestionType = QuestionType.WORKER
        val questions = questionRepository.findByType(type)

        //when
        val result = questionService.getQuestions(type)

        //then
        assertThat(questions).isEqualTo(result)
    }
}