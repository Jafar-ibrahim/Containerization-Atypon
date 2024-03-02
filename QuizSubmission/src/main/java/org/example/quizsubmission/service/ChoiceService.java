package org.example.quizsubmission.service;

import org.example.quizsubmission.model.Choice;
import org.example.quizsubmission.model.Question;
import org.example.quizsubmission.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public Choice addChoice(String text, Question question){
        return choiceRepository.save(new Choice(text,question));
    }
}
