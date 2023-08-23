package org.example.data;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    ENGLISH("en"),
    VIETNAM("vi");

    private String name;
}
