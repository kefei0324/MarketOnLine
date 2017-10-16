package org.feona.commonlib;

/**
 * @AUTHER feona
 * @CREATE 2017/10/16  11:48
 **/
public class UserManager {

    static class UserManagerHolder {
        private static UserManager INSTANCE = new UserManager();
    }

    private UserManager() {
    }

    public static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    private String userName;

    /**
     * @return  开发者id
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置开发者id
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
