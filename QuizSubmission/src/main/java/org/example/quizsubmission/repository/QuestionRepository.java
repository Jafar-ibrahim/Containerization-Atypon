package org.example.quizsubmission.repository;

import org.example.quizsubmission.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT q.correctAnswerId FROM Question q WHERE q.id = :questionId")
    Long findCorrectAnswerIdByQuestionId(@Param("questionId") Long questionId);
}
