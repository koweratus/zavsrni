package repo;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;

public class DataSourceSingleton {

    private static final String SERVER_NAME="den1.mssql8.gear.host";
    private static final String DATABSE_NAME = "zavrsnirad";
    private static final String USER = "zavrsnirad";
    private static final String PASSWORD = "Ul1U6x97M_W_";

    private static DataSource createInstance() {
        SQLServerDataSource dataSource= new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABSE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public DataSourceSingleton() {
    }

    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }
}
