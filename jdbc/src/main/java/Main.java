

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); //jdbc 드라이버 로딩
        Connection connection = DriverManager.getConnection( //connection 객체
                "jdbc:mysql://localhost:3306/mydb",
                "myuser",
                "mypass");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            var user = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age")
            );
            System.out.println(user);
        }
        connection.close();
    }
}
