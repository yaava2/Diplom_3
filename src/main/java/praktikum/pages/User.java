package praktikum.pages;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String name;
    private String email;
    private String password;

    public User( String textName, String textEmail, String textPassword) {
        this.name = textName;
        this.email = textEmail;
        this.password = textPassword;
    }

    public static User random() {
        return new User(RandomStringUtils.randomAlphanumeric(5, 15),
                RandomStringUtils.randomAlphanumeric(5, 10) + "@123.ru",
                RandomStringUtils.randomAlphanumeric(6, 10));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
