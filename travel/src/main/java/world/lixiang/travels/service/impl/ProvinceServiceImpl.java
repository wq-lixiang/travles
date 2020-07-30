package world.lixiang.travels.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.lixiang.travels.dao.ProvinceDao;
import world.lixiang.travels.entity.Province;
import world.lixiang.travels.service.ProvinceService;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceDao provinceDao;

    @Override
    public List<Province> findByPage(Integer start, Integer rows) {
        int  page = (start - 1) * rows;
        return provinceDao.findByPage(page,rows);
    }

    @Override
    public Integer findTotals() {
        return provinceDao.findTotals();
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(0);//景点个数为零
        provinceDao.save(province);
    }

    @Override
    public void delete(String id) {
        provinceDao.delete(id);
    }

    @Override
    public Province findOne(String id) {
        return provinceDao.findOne(id);
    }

    @Override
    public void uploda(Province province) {
        provinceDao.upload(province);
    }
}
