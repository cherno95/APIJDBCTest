import com.mysql.cj.jdbc.JdbcConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperty {
    private static final String FILE_NAME = "/data.properties";

    //Получение данных из файла для подключения к БД
    static String loadProperties(String name) {
        Properties properties = new Properties();
        try (InputStream inputStream = JdbcConnection.class.getResourceAsStream(FILE_NAME)) {
            if (inputStream == null) {
                throw new IOException("Файл " + FILE_NAME + " не найден в ресурсах!");
            }
            properties.load(inputStream);
            return properties.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}