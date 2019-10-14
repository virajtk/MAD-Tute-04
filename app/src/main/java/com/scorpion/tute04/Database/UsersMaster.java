package com.scorpion.tute04.Database;

import android.provider.BaseColumns;

public final class UsersMaster {

    private UsersMaster() {
    }

    protected static class Users implements BaseColumns {

        public static final String TABLE_Name = "users";
        public static final String COLUMN_1 = "username";
        public static final String COLUMN_2 = "password";

    }
}
