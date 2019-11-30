package com.whompum.app.Account;

/**
 * Constants class for Account related info
 */
public class AccountConstants {

    /*CAN NOT INSTANTIATE*/
    private AccountConstants() {
    }

    public int MAX_USERNAME_LENGTH = 12;
    public int MAX_PASSWORD_LENGTH = 20;

    public static final String EMAIL_REGEX =
            "/^[a-zA-Z0-9#_~!$&amp;\\'()*+,;=:.\"(),:;&lt;&gt;@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$/";

    public static final String PASSWORD_REGEX =
            "";
}
