package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static config.Credentials.credentials;

public class OwnerTest {

    @Test
    @Tag("owner")
    void readFromPropertiesTest() {
        String login = credentials.login();
        String password = credentials.password();
        String url = credentials.url();

        System.out.println(login);
        System.out.println(password);
        System.out.println(url + "\n");

        String message = String.format("I try to login as %s with password %s", login, password);

        System.out.println(message);
    }
}
