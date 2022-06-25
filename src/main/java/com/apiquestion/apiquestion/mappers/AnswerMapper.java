package com.apiquestion.apiquestion.mappers;

import com.apiquestion.apiquestion.documents.Answer;

import java.util.Arrays;
import java.util.List;

public class AnswerMapper {

    /**
     *
     * @param line
     * @return
     */
    public static Answer stringLineToResponse(String line){

        String[] lineArray = line.split("\\|");
        String option= lineArray[0].replace(".","");

        lineArray = Arrays.copyOfRange(lineArray,1,lineArray.length);


        boolean valid = false;
        if(option.startsWith("*")){
            valid = true;
            option = option.substring(1);
        }

        List<String> description = Arrays.asList(lineArray);
        Answer response = new Answer(option,lineArray,valid);
        return response;
    }
}
