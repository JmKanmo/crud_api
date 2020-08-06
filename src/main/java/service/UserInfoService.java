package service;

import model.dto.UserInfo;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoService {
    public int addUser(UserInfo userInfo) throws SQLException;

    public List<UserInfo> getUsers() throws SQLException;

    public UserInfo getUser(int id) throws SQLException;

    public int removeUser(int id) throws SQLException;
}
