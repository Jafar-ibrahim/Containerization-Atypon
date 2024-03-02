package org.example.quizsubmission.controllers;

import org.example.quizsubmission.model.AnswersSubmissionDTO;
import org.example.quizsubmission.model.Mark;
import org.example.quizsubmission.model.Quiz;
import org.example.quizsubmission.model.UserAnswerDTO;
import org.example.quizsubmission.service.AnalyticsService;
import org.example.quizsubmission.service.MarkService;
import org.example.quizsubmission.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final MarkService markService;
    private final AnalyticsService analyticsService;

    @Autowired
    public QuizController(QuizService quizService, MarkService markService, AnalyticsService analyticsService) {
        this.quizService = quizService;
        this.markService = markService;
        this.analyticsService = analyticsService;
    }

    @GetMapping("/{quiz_id}")
    public String getQuizView(Model model, @PathVariable Long quiz_id ,
                              @RequestParam(name = "success", required = false) String successMessage){
        Optional<Quiz> quizOptional = quizService.getById(quiz_id);
        Quiz quiz = quizOptional.get();
        List<UserAnswerDTO> userAnswers = initializeUserAnswers(quiz.getQuestions().size());
        AnswersSubmissionDTO answersSubmission = new AnswersSubmissionDTO();
        answersSubmission.setUserAnswers(userAnswers);
        model.addAttribute("answersSubmission", answersSubmission);
        model.addAttribute("quiz", quiz);

        if (successMessage != null) {
            model.addAttribute("success", successMessage);
        }

        return "quiz_view";
    }
    @GetMapping("/init")
    public void initializeExampleQuiz(){
        quizService.initializeExampleQuiz();
    }

    @PostMapping("/submit")
    public String submitAnswers(@ModelAttribute(name = "answersSubmission")AnswersSubmissionDTO userAnswers,
                                @RequestParam Long quizId,RedirectAttributes redirectAttributes) {

        assert userAnswers != null;
        double markValue = quizService.calculateMark(userAnswers);
        Mark mark = new Mark();
        mark.setMark(markValue);mark.setQuiz_id(quizId);
        markService.saveMark(mark);

        analyticsService.notifyNewSubmission(quizId);

        redirectAttributes.addFlashAttribute("success", "Your answers have been saved successfully");
        return "redirect:/quiz/"+quizId;
    }

    private List<UserAnswerDTO> initializeUserAnswers(int numberOfQuestions) {
        List<UserAnswerDTO> userAnswers = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            userAnswers.add(new UserAnswerDTO());
        }
        return userAnswers;
    }
}
