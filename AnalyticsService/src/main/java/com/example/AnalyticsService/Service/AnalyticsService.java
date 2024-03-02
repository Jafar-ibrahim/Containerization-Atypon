package com.example.AnalyticsService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Double getMaxMark(Long quizId) {
        String query = "SELECT MAX(mark) FROM marks WHERE quiz_id = "+quizId;
        return jdbcTemplate.queryForObject(query,Double.class);
    }

    public Double getMinMark(Long quizId) {
        String query = "SELECT MIN(mark) FROM marks WHERE quiz_id = "+quizId;
        return jdbcTemplate.queryForObject(query,Double.class);
    }

    public Double getAvgMark(Long quizId) {
        String query = "SELECT AVG(mark) FROM marks WHERE quiz_id = "+quizId;
        return jdbcTemplate.queryForObject(query,Double.class);
    }

    public Integer getNoOfSubmissions(Long quizId) {
        String query = "SELECT COUNT(*) FROM marks WHERE quiz_id = "+quizId;
        return jdbcTemplate.queryForObject(query,Integer.class);
    }

    public Double getMedianMark(Long quizId){
        String query = "SELECT mark FROM marks WHERE quiz_id = "+quizId;
        List<Double> marks = jdbcTemplate.queryForList(query, Double.class);
        if (marks.isEmpty()) {
            return 0.0;
        }
        Collections.sort(marks);

        int noOfSubmissions = getNoOfSubmissions(quizId);
        if (noOfSubmissions % 2 == 0) {
            double mid1 = marks.get(noOfSubmissions / 2 - 1);
            double mid2 = marks.get(noOfSubmissions / 2);
            return (mid1 + mid2) / 2;
        } else {
            return marks.get(noOfSubmissions / 2);
        }
    }

}
