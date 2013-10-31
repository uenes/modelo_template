package br.template.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uenes
 */
public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                //Register the JDBC driver for MySQL.
                Class.forName("com.mysql.jdbc.Driver");


                //Define URL of database server for
                // database named mysql on the localhost
                // with the default port number 3306.
                String url =
                        "jdbc:mysql://localhost:3306/mysql";

                //Get a connection to the database for a
                // user named root with a blank password.
                // This user is the default administrator
                // having full privileges to do anything.
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(
                            url, "root", "");
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }

                return connection;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
}
