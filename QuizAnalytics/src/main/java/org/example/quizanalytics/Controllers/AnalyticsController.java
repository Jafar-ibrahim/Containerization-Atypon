package org.example.quizanalytics.Controllers;

import org.example.quizanalytics.Model.Analytics;
import org.example.quizanalytics.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/quiz/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/{quizId}")
    public String getAnalyticsView(Model model, @PathVariable Long quizId){
        Optional<Analytics> analytics = analyticsService.getAnalyticsById(quizId);
        analytics.ifPresent(value -> model.addAttribute("analytics", value));

        return "analytics_view";
    }
}
