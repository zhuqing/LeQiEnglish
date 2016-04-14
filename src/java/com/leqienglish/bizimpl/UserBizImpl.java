package com.leqienglish.bizimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.leqienglish.biz.UserBiz;
import com.leqienglish.dao.UserDao;
import com.leqienglish.entity.PageParam;
import com.leqienglish.entity.User;
import com.leqienglish.util.FileUtil;
import com.leqienglish.util.LeQiType;
import com.leqienglish.util.SendEmail;
import com.leqienglish.util.Util;
import com.leqienglish.util.json.JSONUtil;

public class UserBizImpl implements UserBiz {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public String addUser(User user) {
        // TODO Auto-generated method stub
        JSONObject json = new JSONObject();
        if (this.userDao.checkEmail(user.getEmail()) == null) {
            if (userDao.addUser(user)) {
                this.sendActivityEmail(user);
                json.put("msg", "ok");
            } else {
                json.put("msg", "error");
            }
        } else {
            json.put("msg", "error");
        }

        return json.toString();
    }

    /**
     * 发送激活email
     *
     * @param user
     */
    private void sendActivityEmail(User user) {

        if (Util.activityEmail == null) {
            Util.activityEmail = this.findTemplet("/email/emailtemplet.html");
        }

        String emailContent = Util.activityEmail.replaceAll("\\{1\\}",
                user.getUsername());
        emailContent = emailContent.replaceAll("\\{2\\}", "乐琪英语网");
        emailContent = emailContent.replaceAll("\\{3\\}",
                "http://localhost:8080/user/adduser.html?step=3&activityCode="
                + user.getUuid());
        new SendEmail("乐琪英语网邀请您验证邮件", emailContent, user.getEmail()).start();
    }

    private void sendFindPassword(User user) {

        if (Util.findPassword == null) {
            Util.findPassword = this
                    .findTemplet("/email/findpasswordTemplet.html");
        }

        String emailContent = Util.findPassword.replaceAll("\\{1\\}",
                user.getUsername());
        emailContent = emailContent.replaceAll("\\{2\\}", "乐琪英语网");
        emailContent = emailContent.replaceAll("\\{3\\}",
                "http://www.leqisoft.com/user/findPassword.html?step=3&code="
                + user.getUuid());
        new SendEmail("乐琪英语网用户重设密码说明", emailContent, user.getEmail()).start();
    }

    private String findTemplet(String path) {
//		String realpath = ServletActionContext.getRequest().getSession()
//				.getServletContext()
//				.getRealPath(ServletActionContext.getRequest().getRequestURI());
//		String parentPath = realpath.split(Util.projectName)[0]
//				+ Util.projectName + path;
//		String emailContent = FileUtil.readFile(parentPath);
//
//		return emailContent;
        return "";
    }

    private String repalceAll(String content, String... parmeters) {
        for (int i = 0; i < parmeters.length; i++) {
            content = content.replaceAll("\\{" + (i + 1) + "\\}", parmeters[i]);
        }

        return content;
    }

    /**
     * 获取 已经注册的用户
     *
     * @param pageParam
     * @return
     */
    @Override
    public String getAllUser(PageParam pageParam) {
        // TODO Auto-generated method stub
        List<User> list = this.userDao.getAllUser(pageParam);
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        json.element("users", array);
        for (User user : list) {
            JSONObject object = new JSONObject();
            object.put("id", user.getId());
            object.put("username", user.getUsername());

            object.put("email", user.getEmail());
            object.put("sex", user.getSex());
            array.add(object);
        }
        return null;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password, HttpSession session) {

        User user = null;
        if (username.contains("@")) {
            user = this.userDao.loginByEmail(username);
        } else {
            user = this.userDao.loginByUsername(username);
        }

        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute(LeQiType.LOGIN_USER, user);
                try {
                    JSONObject json = putUser2JSON(user);
                    return json.toString();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return JSONUtil.errMessage();
    }

    /**
     * 检测email 是否已经注册过
     *
     * @param email
     * @return
     */
    @Override
    public String checkEmail(String email) {
        // TODO Auto-generated method stu
        JSONObject json = new JSONObject();
        try {
            User user = this.userDao.checkEmail(email);
            if (user != null) {
                json.put("msg", "ok");
            } else {
                json.put("msg", "error");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put("msg", "error");
        }
        return json.toString();
    }

    /**
     * 激活用户
     *
     * @param uuid
     * @return
     */
    @Override
    public String activityUser(String uuid) {
        // TODO Auto-generated method stub
        JSONObject json = new JSONObject();
        User user = this.userDao.getUserByUuid(uuid);
        if (user == null) {
            json.put("msg", "error");
            return json.toString();
        }
        user.setStatus(User.UserType.ACTIVITY);

        if (this.userDao.updateUser(user)) {
            json.put("msg", "ok");
        } else {
            json.put("msg", "error");
        }
        return json.toString();
    }

    @Override
    public String findPassword(String emailOrUserName) {
        User user = null;
        JSONObject json = new JSONObject();
        if (emailOrUserName.contains("@")) {
            user = this.userDao.loginByEmail(emailOrUserName);
        } else {
            user = this.userDao.loginByUsername(emailOrUserName);
        }

        if (user != null) {
            json.put("msg", "ok");
            json.put("mail", user.getEmail().split("@")[1]);
            sendFindPassword(user);

        } else {
            json.put("msg", "error");
        }
        return json.toString();
    }

    @Override
    public String getUserByUuie(String uuid) {
        User user = this.userDao.getUserByUuid(uuid);
        JSONObject json = new JSONObject();
        if (user != null) {
            json.put("msg", "ok");
        } else {
            json.put("msg", "error");
        }
        return json.toString();
    }

    @Override
    public String modifyPassword(String uuid, String password) {
        User user = this.userDao.getUserByUuid(uuid);
        JSONObject json = new JSONObject();
        if (user != null) {
            user.setPassword(password);
            if (this.userDao.updateUser(user)) {
                json.put("msg", "ok");
                json.put("username", user.getUsername());
            } else {
                json.put("msg", "error");
            }
        } else {
            json.put("msg", "error");
        }
        return json.toString();
    }

    @Override
    public String findUserFromSession(HttpSession session) {
        User user = (User) session.getAttribute(LeQiType.LOGIN_USER);

        if (user != null) {
            try {
                JSONObject json = putUser2JSON(user);
                return json.toString();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return JSONUtil.errMessage();
    }

    private JSONObject putUser2JSON(User user) throws Exception {
        JSONObject json = JSONUtil.toJsonObject(user);
        json.put("msg", "ok");

        return json;

    }

    @Override
    public String loginout(HttpSession session) {
        session.setAttribute(LeQiType.LOGIN_USER, null);
        JSONObject json = new JSONObject();
        json.put("msg", "ok");
        return json.toString();
    }

}
