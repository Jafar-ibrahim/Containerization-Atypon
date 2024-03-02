package org.example.quizsubmission.service;

import org.example.quizsubmission.model.Mark;
import org.example.quizsubmission.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkService {
    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public void saveMark(Mark mark){
        markRepository.save(mark);
    }
}
