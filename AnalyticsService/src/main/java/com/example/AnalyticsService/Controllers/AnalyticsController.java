package com.example.AnalyticsService.Controllers;


import com.example.AnalyticsService.Service.AnalyticsService;
import com.example.AnalyticsService.Model.Analytics;
import com.example.AnalyticsService.Repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsRepository analyticsRepository;
    @Autowired
    private AnalyticsService analyticsService;

    @PostMapping("/analyze/{quizId}")
    public void updateAnalytics(@PathVariable Long quizId){
        // to override the current quiz analytics
        analyticsRepository.deleteById(quizId);
        Double maxMark = analyticsService.getMaxMark(quizId);
        Double minMark = analyticsService.getMinMark(quizId);
        Double avgMark = analyticsService.getAvgMark(quizId);
        Double medianMark = analyticsService.getMedianMark(quizId);
        Integer noOfSubmissions = analyticsService.getNoOfSubmissions(quizId);
        Analytics mongoDocument = new Analytics(quizId,maxMark, minMark, avgMark,medianMark, noOfSubmissions);
        analyticsRepository.save(mongoDocument);
    }
}
