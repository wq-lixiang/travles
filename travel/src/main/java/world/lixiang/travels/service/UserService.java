package world.lixiang.travels.service;

import world.lixiang.travels.entity.User;

public interface UserService {

    void save (User user);

    User findByUsername(String username);

    User login(User user);
}
