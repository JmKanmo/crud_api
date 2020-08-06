package service;

import model.dto.UserInfo;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoService {
    int addUser(UserInfo userInfo) throws SQLException;

    List<UserInfo> getUsers() throws SQLException;

    UserInfo getUser(int id) throws SQLException;

    int removeUser(int id) throws SQLException;

    int updateUser(UserInfo userInfo) throws SQLException;
}
