package org.example.quizsubmission.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswersSubmissionDTO implements Serializable {
    private List<UserAnswerDTO> userAnswers;

    public void addAnswer(UserAnswerDTO answerDTO){
        userAnswers.add(answerDTO);
    }

}
