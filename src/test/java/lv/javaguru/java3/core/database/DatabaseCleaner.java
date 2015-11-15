package lv.javaguru.java3.core.database;

/**
 * Created by Anna on 15.11.2015.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCleaner {

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("clients");
        tableNames.add("ideas");
        tableNames.add("attempts");

        tableNames.add("users");
        return tableNames;
    }

    public void cleanDatabase() throws DBException {
        for (String tableName : getTableNames()) {
            Connection connection = getConnection();
            try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + tableName);
                preparedStatement.executeUpdate();
            } catch (Throwable e) {
                System.out.println("Exception while execute cleanDatabase() for table " + tableName);
                e.printStackTrace();
                throw new DBException(e);
            } finally {
                closeConnection(connection);
            }
        }
    }

    protected Connection getConnection() throws DBException {
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_idea_keeper", "Anna", "anna2302");
        } catch (SQLException e) {
            System.out.println("Exciption while getting connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    protected void closeConnection(Connection connection) throws DBException {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exciption while closing connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

}
