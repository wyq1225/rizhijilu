package com.newer.controller;

import com.newer.pojo.User;
import com.newer.service.UserService;
import com.newer.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource(name="user")
    private UserService userService;

    private static final Logger log= LoggerFactory.getLogger(UserController.class);


    /**
     * 新增用户
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<?> add(@RequestBody User user){

        log.info("开始创建用户,用户名：{}",user.getUserName());
        //首先检查该用户是否已存在
        User userx=userService.queryByuserName(user.getUserName());
        if(userx!=null){
            return new ResponseEntity<>("用户名已存在，不能重复创建", HttpStatus.CONFLICT);
        }
        String password = MD5.getInstance().getMD5ofStr(user.getPassword());
        user.setPassword(password);
        Integer i=userService.insert(user);
        String str="创建失败";
        if(i>0){
            str="创建成功";
            log.info("创建成功");
        }
        return new ResponseEntity<>(str,HttpStatus.CREATED);
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/user/{userNamex}/{passwordx}")
    public ResponseEntity<?>login(@PathVariable("userNamex") String userName,@PathVariable("passwordx") String password){
        log.info("开始匹配用户是否正确，用户名：{}",userName);
        //密码MD5加密
        String passwordjiami = MD5.getInstance().getMD5ofStr(password);
        User user=new User();
        user.setPassword(passwordjiami);
        user.setUserName(userName);
        User userx=userService.login(user);
        if(userx==null){
            log.info("输入错误");
            return new ResponseEntity<>("用户名或密码输入错误",HttpStatus.NO_CONTENT);
        }
        log.info("登陆成功");
        return new ResponseEntity<>("登陆成功", HttpStatus.OK);



    }



}
