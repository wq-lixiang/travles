package world.lixiang.travels.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.lixiang.travels.dao.PlaceDao;
import world.lixiang.travels.entity.Place;
import world.lixiang.travels.entity.Province;
import world.lixiang.travels.service.PlaceService;
import world.lixiang.travels.service.ProvinceService;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceDao placeDao;

    @Autowired
    ProvinceService provinceService;

    @Override
    public List<Place> findAllPage(String provinceId, Integer start, Integer rows) {
        Integer page = (start - 1) * rows;
        return placeDao.findAllPage(provinceId,page,rows);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDao.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void save(Place place) {
        //保存景点
        placeDao.save(place);
        //查询原始省份信息
        Province province = provinceService.findOne(place.getProvinceid());
        //更新省份信息的景点个数
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.uploda(province);
    }

    @Override
    public Place findOne(String id) {
        return placeDao.findOne(id);
    }

    @Override
    public void update(Place place) {
        placeDao.update(place);
    }

    @Override
    public void delete(String id) {
        //直接删除景点  更新省份景点个数 删除景点
        Place place = placeDao.findOne(id);
        Province province = provinceService.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.uploda(province);
        //删除景点信息
        placeDao.delete(id);
    }
}
