package lk.ijse.layeredarchitecture.dao;

import lk.ijse.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T executeQuery(String sql , Object... ar) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < ar.length; i++){
            preparedStatement.setObject((i+1) , ar[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T)preparedStatement.executeQuery();
        }else {

            return (T)(Boolean)(preparedStatement.executeUpdate() > 0);
        }
    }
}
