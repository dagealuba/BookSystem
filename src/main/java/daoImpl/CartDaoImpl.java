package daoImpl;


import dao.CartDao;
import util.DBConnectionPool;

import java.sql.*;

public class CartDaoImpl implements CartDao {

    @Override
    public byte[] findByUserId(String userId) {
        Connection connection = new DBConnectionPool().getConnection();
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("select * from cart where userId = '%s'", userId);
            ResultSet res = stmt.executeQuery(sql);
            return res.getBytes("book");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addBookToCart(String userId, byte[] buf) {
        Connection connection = new DBConnectionPool().getConnection();
        String sql = "insert into cart values(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setBytes(2, buf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCart(String userId) {
        addBookToCart(userId, new byte[0]);
    }
}
