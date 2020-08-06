package service;

import model.dao.UserInfoDao;
import model.dto.UserInfo;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl() throws NamingException{
        this.userInfoDao = new UserInfoDao();
    }

    @Override
    public int addUser(UserInfo userInfo) throws SQLException {
        return userInfoDao.insertUser(userInfo);
    }

    @Override
    public List<UserInfo> getUsers() throws SQLException {
        return userInfoDao.selectUsers();
    }

    @Override
    public UserInfo getUser(int id) throws SQLException {
        return userInfoDao.selectUser(id);
    }

    @Override
    public int removeUser(int id) throws SQLException {
        return userInfoDao.deleteUser(id);
    }
}
