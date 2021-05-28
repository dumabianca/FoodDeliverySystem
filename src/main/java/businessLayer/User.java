package businessLayer;

import java.io.Serializable;
/**
 * @Author: Duma Bianca
 * @Since: may 18, 2021
 * Class defining a user that can be either a client, an employee or an administrator
 */
public class User implements Serializable {
    private String username;
    private String password;
    private UserType type;
    private int clientId;
    public User()
    {

    }
    public User(String username, String password, UserType type)
    {
        this.username=username;
        this.password=password;
        this.type=type;
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

    public UserType getType() {
        return type;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "businessLayer.User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", clientId=" + clientId +
                '}';
    }
}
