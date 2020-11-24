package repo;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;

public class DataSourceSingleton {

  //  private static final String SERVER_NAME="DESKTOP-MFUAQE3\\SQLEXPRESS";
    private static final String SERVER_NAME="DESKTOP-9PJTSVI\\SQLEXPRESS";
    private static final String DATABSE_NAME="ZavrsniRad";
    private static final String USER="sa";
    private static final String PASSWORD="SQL";

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
