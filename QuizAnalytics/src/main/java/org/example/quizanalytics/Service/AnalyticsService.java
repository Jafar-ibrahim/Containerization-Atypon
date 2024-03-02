package org.example.quizanalytics.Service;

import org.example.quizanalytics.Model.Analytics;
import org.example.quizanalytics.Repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public Optional<Analytics> getAnalyticsById(Long id){
        return analyticsRepository.findById(id);
    }
}
