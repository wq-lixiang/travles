package world.lixiang.travels.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.lixiang.travels.dao.UserDao;
import world.lixiang.travels.entity.User;
import world.lixiang.travels.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public void save(User user) {
        User user1 =  userDao.findByUsername(user.getUsername());
        if(user1 == null){
            userDao.save(user);
        }else{
            throw new RuntimeException("用户名已经存在~~~~");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User byUsername = userDao.findByUsername(user.getUsername());
        if(byUsername != null){
            if(user.getPassword().equals(byUsername.getPassword())){
                return byUsername;
            }
            throw new RuntimeException("密码错误");
        }else {
            throw new RuntimeException("用户名不存在");
        }

    }
}
