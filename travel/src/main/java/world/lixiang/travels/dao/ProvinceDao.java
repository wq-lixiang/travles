package world.lixiang.travels.dao;

import org.apache.ibatis.annotations.Param;
import world.lixiang.travels.entity.Province;

import java.util.List;

public interface ProvinceDao {

    List<Province> findByPage(@Param("start") Integer start  , @Param("rows") Integer rows);

    Integer findTotals();

    void save(Province province);

    void delete(String id);

    Province findOne(String id);

    void upload(Province province);
}
