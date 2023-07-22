import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class Database
{
    public Database(String username, String password, String email, String sex)
    {
        register(username, password, email, sex);
    }

    private void register(String username, String password, String email, String sex)
    {
        try
        {
//            username = uname.getText();
//            password = upass.getText();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "root");

            // using a string for the SQL query
            String checkQuery = "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, email);
//            preparedStatement.setString(4, sex);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst())
            {
                // JOptionPane.showMessageDialog(this, "This user already exists");
                System.out.println("User with username " + username + " already exists");
            }
            else
            {
                // If no matching row is found, the username and password combination is not in the database
                // Proceed with registration.

                // In the insertQuery, we don't need to pass in the user_id,
                // because it is an AUTO INCREMENT element in the SQL.
                // So each user will have a unique ID that is assigned by SQL.
                String insertQuery = "INSERT INTO users (username, password, email, sex) VALUES (?, ?, ?, ?)";
                PreparedStatement insert = connection.prepareStatement(insertQuery);
                insert.setString(1, username);
                insert.setString(2, password);
                insert.setString(3, email);
                insert.setString(4, sex);
                int rowsInserted = insert.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed!");
                }
                insert.close();
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLIntegrityConstraintViolationException key)
        {
            System.out.println("Username already exists. Registration failed!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}