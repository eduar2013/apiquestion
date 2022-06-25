package com.apiquestion.apiquestion.services;

import lombok.Data;

import java.nio.file.Path;

@Data
public class ListPath {

    private Path path;

    public ListPath(Path path) {
        this.path = path;
    }
}



