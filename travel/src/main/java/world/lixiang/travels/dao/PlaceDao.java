package world.lixiang.travels.dao;

import org.apache.ibatis.annotations.Param;
import world.lixiang.travels.entity.Place;

import java.util.List;

public interface PlaceDao {


    List<Place>  findAllPage(@Param("provinceId") String provinceId , @Param("start") Integer start , @Param("rows") Integer rows);

    Integer findByProvinceIdCounts(String provinceId);

    void save(Place place);

    Place findOne(String id);

    void update(Place place);

    void delete(String id);
}
