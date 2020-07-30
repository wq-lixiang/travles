package world.lixiang.travels.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import world.lixiang.travels.entity.Result;
import world.lixiang.travels.entity.User;
import world.lixiang.travels.service.UserService;
import world.lixiang.travels.utils.ValidateImageCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user, HttpServletRequest request){
        Result result = new Result();

        try{
            User userDB = userService.login(user);
            request.getServletContext().setAttribute(userDB.getId(),userDB);
            result.setMsg("登录成功~~").setUserId(userDB.getId());
        }catch (Exception e){
            result.setMsg(e.getMessage()).setState(false);
        }

        return  result;
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(String code , String key ,  @RequestBody User user ,HttpServletRequest request){
        Result result = new Result();
        log.info("接收的验证码: " + code);
        log.info("接收的验证码的key: " + key);
        log.info("接收到user对象: " + user);

        //验证验证码是否相等
        String keyCode = (String)request.getServletContext().getAttribute(key);
        try{
            if(keyCode.equalsIgnoreCase(code)){
                userService.save(user);
                result.setMsg("用户注册成功");
            }else{
                throw new RuntimeException("验证码错误 ！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }


    @GetMapping("/getImage")
    @ResponseBody
    public Map<String,String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String > result = new HashMap<String, String>();
        //获取验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        //验证码存入application中
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key,securityCode);
        //生成图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;

    }
}
