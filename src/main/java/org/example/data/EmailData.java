package org.example.data;

import lombok.Data;

@Data
public class EmailData {

    private String to;
    private String cc;
    private String subject;
    private String content;
    private String imgPath;
    private String attachmentPath;
}
