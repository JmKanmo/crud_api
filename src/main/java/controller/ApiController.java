package controller;

import model.dto.UserInfo;
import service.UserInfoService;
import service.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ApiController extends HttpServlet {
    private UserInfoService userInfoService;
    private boolean isConnected;

    @Override
    public void init() {
        try {
            userInfoService = new UserInfoServiceImpl();
            isConnected = true;
        } catch (Exception e) {
            e.printStackTrace();
            isConnected = false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=utf-8");

            if (!isConnected) {
                resp.getWriter().println("postgresql is not connected.");
                return;
            }

            if (req.getParameter("id") != null) {
                int id = Integer.parseInt(req.getParameter("id"));
                UserInfo userInfo = userInfoService.getUser(id);

                if (userInfo != null) {
                    resp.getWriter().println(userInfo.toString());
                } else {
                    resp.getWriter().println("Failed to find user info");
                }
            } else {
                List<UserInfo> userInfoList = userInfoService.getUsers();

                if (userInfoList != null) {
                    resp.getWriter().println(userInfoList.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("doGet work failed. please check log");
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=utf-8");

            if (!isConnected) {
                resp.getWriter().println("postgresql is not connected.");
                return;
            }

            UserInfo userInfo = new UserInfo.Builder().name_(req.getParameter("name"))
                    .job_(req.getParameter("job")).hometown_(req.getParameter("hometown")).email_(req.getParameter("email")).build();

            int row = userInfoService.addUser(userInfo);
            resp.getWriter().println("post work is successful, 추가된 행 개수" + row);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("doPost work failed. please check log");
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=utf-8");
            int row = userInfoService.removeUser(Integer.parseInt(req.getParameter("id")));
            resp.getWriter().println("Delete work is successful, 삭제 된 행 개수: " + row);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("deleteWork failed. please check log");
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=utf-8");

            UserInfo userInfo = new UserInfo.Builder().id_(Integer.parseInt(req.getParameter("id")))
                    .name_(req.getParameter("name")).job_(req.getParameter("job"))
                    .hometown_(req.getParameter("hometown")).email_(req.getParameter("email")).build();

            if (userInfoService.updateUser(userInfo) < 0) {
                resp.getWriter().println("update failed, update id 지정이 잘못되었습니다.");
            } else {
                int row = userInfoService.updateUser(userInfo);
                resp.getWriter().println("Put work is successful, 업데이트 행 개수:" + row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("putWork failed. please check log");
            resp.getWriter().println(e.getMessage());
        }
    }
}
