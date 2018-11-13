package util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;


public class DBConnectionPool implements DataSource {

    private BlockingDeque<Connection> connections = new LinkedBlockingDeque<>();

    public DBConnectionPool() {
        for (int i = 0; i < 10; ) {
            Connection connection = DBUtils.getConnection();
            if (connection != null) {
                connections.add(connection);
            } else {
                continue;
            }
            i++;
        }
    }

    @Override
    public Connection getConnection() {
        final Connection conn = connections.removeFirst();

        return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                conn.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("close")) {
                            releaseConnection(conn);
                            return null;
                        } else {
                            return method.invoke(conn, args);
                        }
                    }
                });
    }

    @Override
    public Connection getConnection(String username, String password) {
        return null;
    }

    private void releaseConnection(Connection conn) {
        connections.add(conn);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
