package techtask.nanda.techniciantask.model.account;

/**
 * Created by ASUS on 11/12/2018.
 */

public class AccountUser {
    private String username;
    private String password;

    public AccountUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
