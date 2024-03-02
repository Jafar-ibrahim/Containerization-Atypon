package org.example.quizsubmission.service;

import org.example.quizsubmission.model.Question;
import org.example.quizsubmission.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public void saveQuestion(Question question){
        questionRepository.save(question);
    }
    public Long getCorrectAnswerId(Long questionId){
        return questionRepository.findCorrectAnswerIdByQuestionId(questionId);
    }
}
