package com.apiquestion.apiquestion.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Sequences {

    @Id
    private  String id;
    private  int sequence_value;
}
