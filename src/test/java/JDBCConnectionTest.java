import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

@Slf4j
public class JDBCConnectionTest {

    @BeforeAll
    @SneakyThrows
    public static void connection() {
        DataSql.connectionBD();
    }

    @Test
    public void testRun () {
        createTable();
        outputOfAllRecords();
        outputOfAllRecordsMoreThanOne();
    }


    @SneakyThrows
    public void createTable() {
        DataSql.statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL, age SMALLINT NOT NULL, PRIMARY KEY (id));");
        DataSql.statement.executeUpdate("INSERT INTO Users (name, age) values ('Иван', '25')");
        DataSql.statement.executeUpdate("INSERT INTO Users (name, age) values ('Владимир', '26')");
        DataSql.statement.executeUpdate("INSERT INTO Users (name, age) values ('Петр', '27')");
        DataSql.statement.executeUpdate("INSERT INTO Users (name, age) values ('Игорь', '28')");
        DataSql.statement.executeUpdate("INSERT INTO Users (name, age) values ('Николай', '28')");
    }


    @SneakyThrows
    public void outputOfAllRecords() {
        ResultSet rs1 = DataSql.statement.executeQuery("SELECT * FROM Users");
        System.out.println("Вывод на экран всех записей в таблице Users");
        while (rs1.next()) {
            log.info("------------------------------");
            System.out.println(rs1.getInt(1));
            System.out.println(rs1.getString(2));
        }
    }


    @SneakyThrows
    public void outputOfAllRecordsMoreThanOne() {
        System.out.println();
        ResultSet rs = DataSql.statement.executeQuery("SELECT * FROM Users WHERE id > 1");
        System.out.println("Вывод на экран id > 1 из таблицы Users");
        while (rs.next()) {
            log.info("------------------------------");
            System.out.println("userName: " + rs.getString("name"));
            System.out.println("age: " + rs.getInt("age"));
        }
    }


    @AfterAll
    @SneakyThrows
    public static void dropTable() {
        log.info("Удалить таблицу Users с данными");
        DataSql.statement.executeUpdate("DROP TABLE IF EXISTS Users");
    }
}