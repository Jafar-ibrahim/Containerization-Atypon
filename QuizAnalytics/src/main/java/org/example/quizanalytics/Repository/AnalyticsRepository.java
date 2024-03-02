package org.example.quizanalytics.Repository;

import org.example.quizanalytics.Model.Analytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends MongoRepository<Analytics, Long> {
}

