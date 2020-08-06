package model.dao;

import model.dto.UserInfo;
import model.sql.DaoSqls;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDao {
    private DataSource dataSource;

    public UserInfoDao() throws NamingException {
        init();
    }

    public void init() throws NamingException {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/postgres");
        } catch (NamingException namingException) {
            throw new NamingException();
        }
    }

    public int insertUser(UserInfo userInfo) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) connection.prepareStatement(DaoSqls.INSERT_USER);) {
            ps.setString(1, userInfo.getName());
            ps.setString(2, userInfo.getJob());
            ps.setString(3, userInfo.getHometown());
            ps.setString(4, userInfo.getEmail());
            ps.setTimestamp(5, userInfo.getCreatedOn());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<UserInfo> selectUsers() throws SQLException {
        List<UserInfo> userInfoList = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) connection.prepareStatement(DaoSqls.SELECT_ALL_USER);
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserInfo userInfo = new UserInfo.Builder().id_(rs.getInt("id")).name_(rs.getString("name"))
                            .job_(rs.getString("job")).hometown_(rs.getString("hometown"))
                            .email_(rs.getString("email")).createdOn_(rs.getTimestamp("created_on")).build();

                    userInfoList.add(userInfo);
                }
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return userInfoList;
    }

    public UserInfo selectUser(int id) throws SQLException {
        UserInfo userInfo = null;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(DaoSqls.SELECT_USER);
        ) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    userInfo = new UserInfo.Builder().id_(rs.getInt("id"))
                            .name_(rs.getString("name")).job_(rs.getString("job"))
                            .hometown_(rs.getString("hometown")).email_(rs.getString("email"))
                            .createdOn_(rs.getTimestamp("created_on")).build();
                }
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return userInfo;
    }

    public int deleteUser(int id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = (PreparedStatement) connection.prepareStatement(DaoSqls.DELETE_USER);) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public int updateUser() {
        return 0;
    }
}
