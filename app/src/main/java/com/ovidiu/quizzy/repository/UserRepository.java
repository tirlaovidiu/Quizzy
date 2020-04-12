package com.ovidiu.quizzy.repository;

import android.app.Application;

import com.ovidiu.quizzy.db.dao.QuizDao;
import com.ovidiu.quizzy.db.model.QuestionAndAllAnswers;
import com.ovidiu.quizzy.db.model.QuizAndAllQuestions;
import com.ovidiu.quizzy.model.Answer;
import com.ovidiu.quizzy.model.ContentType;
import com.ovidiu.quizzy.model.Question;
import com.ovidiu.quizzy.model.Quiz;
import com.ovidiu.quizzy.model.Result;
import com.ovidiu.quizzy.utils.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    @Inject
    QuizDao quizDao;

    @Inject
    Application app;

    @Inject
    public UserRepository() {
    }

//TODO inject service here

    public Quiz getQuiz(String quizId) {
        Util.writeFileOnInternalStorage(app.getApplicationContext(), "test.file", "testt");
        String backgroundUrl = "https://static3.thequizimages.com/wordpress/wp-content/uploads/2018/06/Wonder-Woman-CREDIT-Buddha-Jones-1.jpg";
        String backgroundUrl2 = "https://static1.thequizimages.com/wordpress/wp-content/uploads/2018/06/Black_Widow_Avengers.jpg";
        String backgroundUrl3 = "https://static3.thequizimages.com/wordpress/wp-content/uploads/2018/06/scarlet-witch-is-described-as-a-wild-card-in-captain-america-civil-war.jpeg";
        String backgroundUrl4 = "https://static1.thequizimages.com/wordpress/wp-content/uploads/2018/06/8d782182b38b0fd72453a368decfd1a4-1.jpg";
        String backgroundUrl5 = "https://static0.thequizimages.com/wordpress/wp-content/uploads/2018/06/legends-ciara-renee.jpg";
        String backgroundUrl6 = "https://static1.thequizimages.com/wordpress/wp-content/uploads/2018/06/913Smallville0648.jpg";
        int i = 0;
        Quiz quiz = new Quiz();
        quiz.setId(String.valueOf(i));
        quiz.setName("Rate These Female Superheroes And We’ll Guess Your Favorite One");
        quiz.setBackgroundUrl("https://images.pexels.com/photos/1300526/pexels-photo-1300526.jpeg");


        Answer answer = new Answer("ID", ContentType.IMAGE_WITH_TEXT, "Wonder Woman", backgroundUrl, 1);
        Answer answer2 = new Answer("ID2", ContentType.IMAGE_WITH_TEXT, "Black Widow", backgroundUrl2, 2);

        Answer answer3 = new Answer("ID3", ContentType.IMAGE_WITH_TEXT, "Scarlet Witch", backgroundUrl3, 1);
        Answer answer4 = new Answer("ID4", ContentType.IMAGE_WITH_TEXT, "Batgirl", backgroundUrl4, 2);
        Answer answer5 = new Answer("ID5", ContentType.IMAGE_WITH_TEXT, "Hawkgirl", backgroundUrl5, 2);
        Answer answer6 = new Answer("ID6", ContentType.IMAGE_WITH_TEXT, "Zatanna", backgroundUrl6, 2);
        Answer answer7 = new Answer("ID7", ContentType.IMAGE_WITH_TEXT, "NO PHOTO ANSWER", 2);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);

        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(answer3);
        answers2.add(answer4);
        answers2.add(answer5);
        answers2.add(answer6);
        answers2.add(answer7);

        Question question = new Question("Who you think is the most smart hero?", answers);
        Question question2 = new Question("Who you think is the most stupid hero?", answers2);
        Question question3 = new Question("Question3", answers2);

        List<Question> questions = new ArrayList<>();
        questions.add(question);
        questions.add(question2);
        questions.add(question3);

        Result result = new Result("ID1", ContentType.IMAGE_WITH_TEXT, "Title1", "description", backgroundUrl, 5);
        Result result2 = new Result("ID2", ContentType.IMAGE_WITH_TEXT, "Title2", "description", backgroundUrl, 6);
        Result result3 = new Result("ID3", ContentType.IMAGE_WITH_TEXT, "Title3", "description", backgroundUrl, 4);
        Result result4 = new Result("ID4", ContentType.IMAGE_WITH_TEXT, "Title4", "description", backgroundUrl, 3);

        List<Result> results = new ArrayList<>();
        results.add(result);
        results.add(result2);
        results.add(result3);
        results.add(result4);

        quiz.setQuestions(questions);
        quiz.setResults(results);
        return quiz;
    }
}
