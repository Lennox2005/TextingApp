
    import java.util.regex.Pattern;

    public class TextingApp {

        private String storedUsername;
        private String storedPassword;
        private final String firstName;
        private final String lastName;

        public TextingApp(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public boolean checkUserName(String username) {
            return username.contains("_") && username.length() <= 5;
        }

        public boolean checkPasswordComplexity(String password) {
            boolean length = password.length() >= 8;
            boolean capital = password.matches(".*[A-Z].*");
            boolean number = password.matches(".*\\d.*");
            boolean special = password.matches(".*[^a-zA-Z0-9].*");

            return length && capital && number && special;
        }

        public boolean checkCellPhoneNumber(String number) {
            return Pattern.matches("^\\+27\\d{9}$", number);
        }

        public String registerUser(String username, String password, String phone) {

            if (!checkUserName(username)) {
                return "Username is not correctly formatted; must contain '_' and be ≤ 5 characters.";
            }

            if (!checkPasswordComplexity(password)) {
                return "Password must be at least 8 characters, include a capital letter, number, and special character.";
            }

            if (!checkCellPhoneNumber(phone)) {
                return "Cell phone number must be in format +27XXXXXXXXX.";
            }

            storedUsername = username;
            storedPassword = password;

            return "User registered successfully.";
        }

        public boolean loginUser(String username, String password) {
            return username.equals(storedUsername) && password.equals(storedPassword);
        }

        public String returnLoginStatus(boolean loginStatus) {
            if (loginStatus) {
                return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
            } else {
                return "Username or password incorrect, please try again.";
            }
        }

        public static void main(String[] args) {

            TextingApp user = new TextingApp("John", "Doe");

            System.out.println(user.checkUserName("kyl_1"));
            System.out.println(user.checkUserName("kyle!!!!!!"));

            System.out.println(user.checkPasswordComplexity("Ch&&sec@ke99!"));
            System.out.println(user.checkPasswordComplexity("password"));

            System.out.println(user.checkCellPhoneNumber("+27838968976"));
            System.out.println(user.checkCellPhoneNumber("08966553"));

            System.out.println(user.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"));

            boolean success = user.loginUser("kyl_1", "Ch&&sec@ke99!");
            System.out.println(success);
            System.out.println(user.returnLoginStatus(success));

            boolean fail = user.loginUser("wrong", "wrong");
            System.out.println(fail);
            System.out.println(user.returnLoginStatus(fail));
        }
    }
