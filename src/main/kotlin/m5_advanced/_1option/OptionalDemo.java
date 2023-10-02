package m5_advanced._1option;

import java.util.Optional;

public class OptionalDemo {

    record GitHubUser(String login, int id) {
    }

    public static void main(String[] args) {

        Optional<GitHubUser> user = getUser();

        user.ifPresent(
                u -> System.out.println("Hello " + u.login)
        );

        var substituteUser = user.orElse(new GitHubUser("Maria", 456));

        System.out.println(substituteUser);

    }

    private static Optional<GitHubUser> getUser() {
        try {
            // success
            return Optional.of(new GitHubUser("andrejs-ps", 123));
        } catch (Exception e) {
            // failure
            return Optional.empty();
        }
    }
}
