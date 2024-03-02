package org.example.quizsubmission.service;

import org.example.quizsubmission.model.*;
import org.example.quizsubmission.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
   private final QuizRepository quizRepository;
   private final QuestionService questionService;
   private final ChoiceService choiceService;

   @Autowired
   public QuizService(QuizRepository quizRepository, QuestionService questionService, ChoiceService choiceService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
        this.choiceService = choiceService;
    }

    public Optional<Quiz> getById(Long quizId){
       return quizRepository.findById(quizId);
   }

   public int calculateMark(AnswersSubmissionDTO userAnswers){
       int mark = 0;
       if(userAnswers != null && !userAnswers.getUserAnswers().isEmpty()){
           for(UserAnswerDTO answer : userAnswers.getUserAnswers()){
               Long questionId = answer.getQuestionId();
               Long choiceId = answer.getSelectedChoiceId();
               Long correctAnswerId = questionService.getCorrectAnswerId(questionId);
               if (Objects.equals(choiceId, correctAnswerId)){
                   mark++;
               }
           }
       }
       return mark;
   }

    public void initializeExampleQuiz() {
        Quiz quiz = new Quiz();
        quizRepository.save(quiz);
        List<Question> questions = new ArrayList<>();
        questions.add(createQuestion("Which environment variable is used to set the java path?",
                        Arrays.asList("MAVEN_Path",
                                "JavaPATH",
                                "JAVA",
                                "JAVA_HOME"),
                        4,quiz));
        questions.add(createQuestion("Which of these keywords is used to define interfaces in Java?",
                        Arrays.asList("intf",
                                "Intf",
                                "interface",
                                "Interface"),
                        3,quiz));
        questions.add(createQuestion("Which of the following is a superclass of every class in Java?",
                        Arrays.asList("ArrayList",
                                "Abstract class",
                                "Object class",
                                "String"),
                        3,quiz));
        questions.add(createQuestion("Which of these packages contains the exception Stack Overflow in Java?",
                        Arrays.asList("java.io",
                                "java.system",
                                " java.lang",
                                "java.util"),
                        3,quiz));
        questions.add(createQuestion("Which of these keywords are used for the block to be examined for exceptions?",
                        Arrays.asList("check",
                                "throw",
                                "catch",
                                "try"),
                        4,quiz));
        questions.add(createQuestion("What is the numerical range of a char data type in Java?",
                        Arrays.asList("0 to 256",
                                "-128 to 127",
                                "0 to 65535",
                                "0 to 32767"),
                        3,quiz));

        quiz.setName("Java Quiz");
        quiz.setQuestions(questions);
        quiz.setMaxScore(questions.size());
        quizRepository.save(quiz);
    }
    public Question createQuestion(String text , List<String> choicesText, int correctAnswerListOrder,Quiz quiz){
        Question question = new Question();
        question.setQuiz(quiz);
        questionService.saveQuestion(question);
        List<Choice> choices = new ArrayList<>();
        for(String s : choicesText){
            choices.add(choiceService.addChoice(s,question));
        }
        question.setQuestionText(text);
        question.setChoices(choices);
        questionService.saveQuestion(question);
        question.setCorrectAnswerId(choices.get(correctAnswerListOrder-1).getId());
        questionService.saveQuestion(question);
        return question;
    }
}
