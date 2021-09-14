package viewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_BASE_URL = "jdbc:sqlite:";

    public void getTablesList() {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_BASE_URL + SQLiteViewer.dbFile.getAbsolutePath());

            stmt = conn.createStatement();

            String sql = "SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SQLiteViewer.tablesList.add(rs.getString("name"));
            }

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (Exception se) {
            se.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public void getTableData(String tableName) {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_BASE_URL + SQLiteViewer.dbFile.getAbsolutePath());

            stmt = conn.createStatement();

            String sql = "SELECT * FROM " + tableName + ";";


            ResultSet resultSet = stmt.executeQuery(sql);
            int columnsCount = resultSet.getMetaData().getColumnCount();

            List<ArrayList<String>> rows = new ArrayList<>();
            while (resultSet.next()) {

                ArrayList<String> record = new ArrayList<>();

                for (int i = 1; i <= columnsCount; i++) {
                    String value = resultSet.getString(i);
                    record.add(value);
                }

                rows.add(record);
            }


            SQLiteViewer.rows = rows;

            resultSet = stmt.executeQuery(sql);
            List<String> columns = new ArrayList<>();

            for (int i = 1; i <= columnsCount; i++ ) {
                String name = resultSet.getMetaData().getColumnName(i);
                columns.add(name);
            }

            SQLiteViewer.columns = columns;

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (Exception se) {
            se.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}


