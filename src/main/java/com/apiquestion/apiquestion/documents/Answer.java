package com.apiquestion.apiquestion.documents;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private String option;
    private String[] description;
    private boolean valid;
}
