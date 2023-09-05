package org.example.data.agoda;

import lombok.Data;

@Data
public class UserData {

    private String email = "linh.nguyen5340@gmail.com";
    private String key;

    @Override
    public String toString() {
        return "Email: " + this.email + " - " + "Password: " + this.key.replaceAll("\\w", "*");
    }
}
