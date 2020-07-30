package world.lixiang.travels.dao;

import world.lixiang.travels.entity.User;

public interface UserDao {

    void save (User user);

    User findByUsername(String username);


}
