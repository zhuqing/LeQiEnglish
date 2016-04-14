package com.leqienglish.action;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.leqienglish.biz.UserBiz;
import com.leqienglish.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAction {

    private UserBiz userBiz;
    private String userName;
    private String email;
    private Integer sex;
    private String password;
    /**
     * uuid激活码
     */
    private String activityCode;
    private String description;
    private String json;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getSex() {
        return sex;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }

    /**
     * **
     * 用户注册
     *
     * @return
     */
    public String addUser() {
        User user = new User();
        user.setCreateTime(Calendar.getInstance().getTimeInMillis() + "");
        user.setEmail(this.email);

        user.setUuid(UUID.randomUUID().toString());
        user.setStatus(User.UserType.UN_ACTIVITY);
        user.setPassword(this.password);

        if (this.userName == null) {
            this.userName = this.email;
        }

        user.setUsername(this.userName);
        this.json = userBiz.addUser(user);
        return "ok";
    }

    /**
     * 激活用户
     *
     * @return
     */
    public String activityUser() {
        this.json = this.userBiz.activityUser(this.activityCode);
        return "ok";
    }

    /**
     * ********
     * 用户登录
     *
     * @return
     */

    public String login() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession(true);
//        this.json = this.userBiz.login(userName, password, session);
        return "ok";
    }

    /**
     * 退出
     *
     * @return
     */
    public String loginOut() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession(true);
//        this.json = this.userBiz.loginout(session);
        return "ok";

    }

    public String findUser() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession(true);
//        this.json = this.userBiz.findUserFromSession(session);
        return "ok";
    }

//	public String addAddress(){
//		Address address = new Address();
//		address.setAreaId(areaId);
//		address.setCityId(cityId);
//		address.setContryId(contryId);
//		address.setDescription(description);
//		address.setProviceId(proviceId);
//		Long addressId = userBiz.addAddress(address);
//		JSONObject json = new JSONObject();
//		json.element("id", addressId);
//		this.json = json.toString();
//		return "addok";
//	}
    public String checkEmail() {
        this.json = this.userBiz.checkEmail(email);
        return "ok";
    }

    /**
     * 查找密码
     *
     * @return
     */
    public String findPassword() {
        this.json = this.userBiz.findPassword(email);
        return "ok";
    }

    public String checkUserByUuid() {
        this.json = this.userBiz.getUserByUuie(activityCode);
        return "ok";
    }

    public String modifyPassword() {
        this.json = this.userBiz.modifyPassword(activityCode, password);

        return "ok";
    }

    public String getAllUser() {
        return "ok";
    }
}
