package com.example.user.testserver;

import android.provider.BaseColumns;

/**
 * Created by user on 2016-07-22.
 */
public class Databases {
    public static final class CreateDB implements BaseColumns {
        public static final String TITLE = "title";
        public static final String TEXT = "text";
        public static final String TIME = "time";
        public static final String _TABLENAME = "ErrMsg";
        public static final String _CREATE =
                "create table "+_TABLENAME+"(ID "+
                        "integer primary key autoincrement, "
                        +TITLE+" text not null , "
                        +TEXT+" text not null , "
                        +TIME+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
    }
}
