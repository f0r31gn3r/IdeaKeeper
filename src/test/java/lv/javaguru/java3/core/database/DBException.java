package lv.javaguru.java3.core.database;

/**
 * Created by Anna on 15.11.2015.
 */
public class DBException extends Exception{

        public DBException(String message) {
            super(message);
        }

        public DBException(String message, Throwable cause) {
            super(message, cause);
        }

        public DBException(Throwable cause) {
            super(cause);
        }
}
