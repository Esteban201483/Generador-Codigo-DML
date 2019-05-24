package Build;

/**
 * This class allows the user to specify the configuration of the database.
 */
public class DataBaseConfiguration extends Configuration {

    private String userName;
    private String password;
    private boolean mySQLCode;
    private boolean postgreSQL;

    public DataBaseConfiguration()
    {
        userName = "";
        password = "";
        mySQLCode = false;
        postgreSQL = false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMySQLCode() {
        return mySQLCode;
    }

    public void setMySQLCode(boolean mySQLCode) {
        this.mySQLCode = mySQLCode;
    }

    public boolean isPostgreSQL() {
        return postgreSQL;
    }

    public void setPostgreSQL(boolean postgreSQL) {
        this.postgreSQL = postgreSQL;
    }
}
