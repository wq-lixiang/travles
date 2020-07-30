package world.lixiang.travels.controller;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.lixiang.travels.entity.Province;
import world.lixiang.travels.entity.Result;
import world.lixiang.travels.service.ProvinceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @PostMapping("upload")
    public Result upload(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.uploda(province);
            result.setMsg("修改成功");
        }catch (Exception e){
            result.setState(false).setMsg("修改失败");
        }
        return result;
    }


    @GetMapping("findOne")
    public Map<String , Object> findOne(String id){
        Map<String , Object> map = new HashMap<>();
        try{
            Province province =  provinceService.findOne(id);
            map.put("province",province);
            if(province == null){
                throw new RuntimeException("获取省份失败");
            }
        }catch (Exception e){
                map.put("msg",e.getMessage());
        }
        return map;
    }

    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            provinceService.delete(id);
            result.setMsg("删除省份成功");

        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("删除省份失败");
        }

        return result;
    }

    @PostMapping("add")
    public Result add(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.save(province);
            result.setMsg("保存省份信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("保存省份信息失败!!!");
        }
        return result;
    }

    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page , Integer rows){
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;

        Map<String,Object> map = new HashMap<String ,Object>();
        //获取当前页的内容
        List<Province> provinces =  provinceService.findByPage(page,rows);
        //总条数
        Integer total =  provinceService.findTotals();
        //一共有多少页
        System.out.println(total);
        Integer totalPage = total%rows == 0 ? total/rows : total/rows + 1;
        map.put("provinces",provinces);
        map.put("total" , total);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }
}
