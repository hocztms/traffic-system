package trafficsystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trafficsystem.commons.RestResult;
import trafficsystem.service.IllegalService;
import trafficsystem.utils.ResultUtils;
import trafficsystem.vo.IllegalVo;
import trafficsystem.vo.IllegalsVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/index")
@Slf4j
public class IllegalController {
    @Autowired
    private IllegalService illegalService;

    @PostMapping("/input")
    public RestResult inputData(@RequestParam("file")MultipartFile file,@RequestParam("jsonData")String jsonData){
        if (file.isEmpty()){
            return ResultUtils.error("文件不能为空");
        }
        IllegalsVo illegalsVo= null;
        try{
            illegalsVo =  JSONObject.parseObject(jsonData,IllegalsVo.class);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error("json解析失败");
        }
        return ResultUtils.success();
    }

    @GetMapping("/output")
    public RestResult outputData(){
        IllegalsVo illegalVo = new IllegalsVo();
        illegalVo.setDate(new Date());
        illegalVo.setEquipmentName("福大生活三区");
        List<IllegalVo> illegalVoList = new ArrayList<>();
        illegalVoList.add(new IllegalVo(0,new Date(),new Date(),"闽A018M4"));
        illegalVo.setIllegals(illegalVoList);
        return ResultUtils.success(illegalVo);
    }
}
