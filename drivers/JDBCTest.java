import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/pnd_analysis";
        String username = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("JDBC URL test successful. Connected to the database.");

            // Get the database metadata
            DatabaseMetaData metaData = connection.getMetaData();

            // Get the list of tables
            ResultSet resultSet = metaData.getTables(null, null, "%", new String[] { "TABLE" });

            // Print the table names
            System.out.println("List of Tables:");
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);
            }

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("JDBC URL test failed. Error: " + e.getMessage());
        }
    }
}

