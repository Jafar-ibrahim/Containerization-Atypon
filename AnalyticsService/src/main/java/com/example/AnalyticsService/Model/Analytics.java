package com.example.AnalyticsService.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Analytics")
public class Analytics {
    @Id
    private Long id;

    private Double maxMark;

    private Double minMark;

    private Double avgMark;

    private Double medianMark;

    private Integer noOfSubmissions;

}
