package service;

import model.dao.UserInfoDao;
import model.dto.UserInfo;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl() throws NamingException {
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

    @Override
    public int updateUser(UserInfo userInfo) throws SQLException {
        if (userInfo.getId() == 0) {
            return -1;
        } else {
            UserInfo saveUserInfo = userInfoDao.selectUser(userInfo.getId());
            if (saveUserInfo == null) return -1;
            if (userInfo.getName() != null) saveUserInfo.setName(userInfo.getName());
            if (userInfo.getJob() != null) saveUserInfo.setJob(userInfo.getJob());
            if (userInfo.getHometown() != null) saveUserInfo.setHometown(userInfo.getHometown());
            if (userInfo.getEmail() != null) saveUserInfo.setEmail(userInfo.getEmail());
            return userInfoDao.updateUser(saveUserInfo);
        }
    }
}
