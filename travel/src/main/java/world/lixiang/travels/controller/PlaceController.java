package world.lixiang.travels.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import world.lixiang.travels.entity.Place;
import world.lixiang.travels.entity.Result;
import world.lixiang.travels.service.PlaceService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private  PlaceService placeService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 查询景点信息
     */
    @GetMapping("findOne")
    public Place findOne(String id){
        return placeService.findOne(id);
    }

    /**
     * 修改景点信息
     */
    @PostMapping("update")
    public Result update(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();

        try{
            //对接收文件做base64
            if(pic != null){
                String picpath = Base64Utils.encodeToString(pic.getBytes());
                place.setPicpath(picpath);
                //处理文件上传
                String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"."+ extension;
                pic.transferTo(new File(realPath,newFileName));
            }else{
                Place place1 = placeService.findOne(place.getId());
                place.setPicpath(place1.getPicpath());
            }

            //修改景点信息
            placeService.update(place);
            result.setMsg("修改景点信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

        @GetMapping("/findAllPage")
        public Map<String,Object> findAllPage(String provinceId,Integer page,Integer rows ){

            page = page == null ? 1 : page;
            rows = rows == null ? 4 :rows;

            Map<String, Object> map = new HashMap<String,Object>();

            //获取得到的Place集合
            List<Place> places =  placeService.findAllPage(provinceId,page,rows);
            //获取一共的记录
            Integer total =  placeService.findByProvinceIdCounts(provinceId);
            //一共有多少页
            Integer pageCount = total%rows == 0 ? total/rows : total/rows+1;

            System.out.println(total);
            System.out.println(pageCount);

            map.put("places", places);
            map.put("page", page);
            map.put("total", total);
            map.put("pageCount", pageCount);
            return map;
        }

        @PostMapping("/save")
        public Result save(MultipartFile pic , Place place){
            Result result = new Result();
            try{
                //文件上传
                String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"." + extension;
                place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
                File file = new File(realPath);
                pic.transferTo(new File(file,newFileName));

                //保存place对象
                placeService.save(place);
                result.setMsg("保存景点信息成功!!!");
            } catch (Exception e) {
                e.printStackTrace();
                result.setState(false).setMsg(e.getMessage());
            }


            return result;
        }

    /**
     * 删除景点信息
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            placeService.delete(id);
            result.setMsg("删除景点信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

}
