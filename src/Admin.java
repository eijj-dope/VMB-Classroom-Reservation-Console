public class Admin {
    private final String fixedUsername = "russel";
    private final String fixedPassword = "admin123";

    public boolean login(String username, String password) {
        return username.equals(fixedUsername) && password.equals(fixedPassword);
    }

    public String getFixedUsername() {
        return fixedUsername;
    }
}
