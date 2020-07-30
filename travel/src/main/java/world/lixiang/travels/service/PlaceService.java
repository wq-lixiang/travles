package world.lixiang.travels.service;

import org.apache.ibatis.annotations.Param;
import world.lixiang.travels.entity.Place;

import java.util.List;

public interface PlaceService {
    List<Place> findAllPage(String provinceId , Integer page ,  Integer rows);

    Integer findByProvinceIdCounts(String provinceId);

    void save(Place place);

    Place findOne(String id);

    void update(Place place);

    void delete(String id);
}
