
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSql {
    private static String userName = LoadProperty.loadProperties("name");
    private static String password = LoadProperty.loadProperties("password");
    private static String connectionUrl = LoadProperty.loadProperties("connectionUrl");
    private static Connection connection = null;
    static Statement statement = null;

    public static void connectionBD() throws ClassNotFoundException, SQLException {
        System.out.println("Подключение к БД . . . . .");
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("Вы успешно подключились к БД");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new SQLException("Ошибка подключения к БД");
        }
    }
}
