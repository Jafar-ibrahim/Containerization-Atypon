package org.example.quizsubmission.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDTO implements Serializable {
    private Long questionId;
    private Long selectedChoiceId;
}
