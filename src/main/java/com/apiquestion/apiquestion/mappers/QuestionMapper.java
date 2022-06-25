package com.apiquestion.apiquestion.mappers;

import com.apiquestion.apiquestion.documents.Answer;
import com.apiquestion.apiquestion.documents.Question;
import com.apiquestion.apiquestion.documents.Topic;
import com.apiquestion.apiquestion.exceptions.QuestionFormatException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    private static final String SEPARATOR_RESPONSES = "--options--";
    private static final String SEPARATOR_OBJECTIVE = "--objective--";
    private static final String SEPARATOR_TOPIC = "--topic--";
    private static final String SEPARATOR_NUMBER = "--number--";

    public static Question fileToQuestion(Path pathFile){

        List<String> lines = null;
        try {
            lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int responsesPosition = lines.indexOf(SEPARATOR_RESPONSES);
        int objectivePosition = lines.indexOf(SEPARATOR_OBJECTIVE);
        int topicPosition = lines.indexOf(SEPARATOR_TOPIC);
        int numberPositiion = lines.indexOf(SEPARATOR_NUMBER);

        /*if(responsesPosition == -1 || objectivePosition == -1 || topicPosition == -1){
            throw new QuestionFormatException("File Format not Valid", pathFile.getFileName());
        }*/

        List<String> questionLst = lines.subList(0,responsesPosition);
        List<String> optionsLst = lines.subList(responsesPosition + 1, objectivePosition);
        String objective = lines.get(objectivePosition + 1);
        String[] stTopic = lines.get(topicPosition+1).split("-");
        int questionNumber = Integer.parseInt(lines.get(numberPositiion+1));



        Question question = new Question();

        question.setText_question(questionLst.toArray(new String[questionLst.size()]));
        question.setObjective(objective);
        question.setTopic(new Topic(Integer.parseInt(stTopic[0]),stTopic[1]));
        question.setNumber(questionNumber);

         List<Answer> listAnswers = optionsLst.stream()
                 .map(AnswerMapper::stringLineToResponse)
                 .collect(Collectors.toList());


        question.setAnswers(listAnswers.toArray(new Answer[listAnswers.size()]));

        return question;
    }
}
