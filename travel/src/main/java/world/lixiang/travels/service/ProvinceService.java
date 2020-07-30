package world.lixiang.travels.service;

import org.apache.ibatis.annotations.Param;
import world.lixiang.travels.entity.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> findByPage(Integer start  , Integer rows);

    Integer findTotals();

    void save(Province province);

    void delete(String id);

    Province findOne(String id);

    void uploda(Province province);
}
