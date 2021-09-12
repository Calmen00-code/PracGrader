package com.calmen.pracgrader.database;

public class DBSchema {
    public static class AdminTable {
        public static final String NAME = "ADMINS";
        public static class Cols {
            public static final String NAME = "name";
            public static final String PIN = "pin";
        }
    }

    public static class InstructorTable {
        public static final String NAME = "INSTRUCTORS";
        public static class Cols {
            public static final String NAME = "name";
            public static final String USERNAME = "username";
            public static final String EMAIL = "email";
            public static final String PIN = "pin";
            public static final String COUNTRY = "country";
            public static final String COUNTRY_FLAG = "flag";
        }
    }

    public static class StudentTable {
        public static final String NAME = "STUDENTS";
        public static class Cols {
            public static final String NAME = "name";
            public static final String USERNAME = "username";
            public static final String EMAIL = "email";
            public static final String PIN = "pin";
            public static final String LAB_UNIT = "lab_unit";
            public static final String MARK = "mark";
            public static final String COUNTRY = "country";
            public static final String COUNTRY_FLAG = "flag";
        }
    }

    public static class PracticalTable {
        public static final String NAME = "PRACTICALS";
        public static class Cols {
            public static final String TITLE = "title";
            public static final String MARK = "mark";
        }
    }
}
