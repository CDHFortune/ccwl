package com.wyh.ccwl.controller;

import com.wyh.ccwl.bean.RespBean;
import com.wyh.ccwl.bean.TrainingRecords;
import com.wyh.ccwl.service.TrainingRecordsService;
import com.wyh.ccwl.util.Authority;
import com.wyh.ccwl.util.LogUtil;
import com.wyh.ccwl.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
public class TrainingRecordsController {
    @Resource
    TrainingRecordsService trainingRecordsService;
    @Authority("2")
    @PostMapping("/getTrainingRecords")
    public RespBean getTrainingRecords(String dept, String team, String jobNumber, String name,
                                       String startDate, String endDate, Integer page, Integer limit){
        try{
            List<TrainingRecords> trainingRecordsList;
            if(page != null && limit != null){
                trainingRecordsList = trainingRecordsService.getTrainingRecordsPage(dept,team,jobNumber,name,startDate,endDate,(page-1)*limit,limit);
            }else{
                trainingRecordsList = trainingRecordsService.getTrainingRecords(dept,team,jobNumber,name,startDate,endDate);
            }
            if(trainingRecordsList != null){
                for (int i = 0;i<trainingRecordsList.size();i++){
                    trainingRecordsList.get(i).setId(i+1+"");
                }
            }
            Integer trainingRecordsCount = trainingRecordsService.getTrainingRecordsCount(dept,team,jobNumber,name,startDate,endDate);
            return new RespBean(trainingRecordsList,trainingRecordsCount!=null?trainingRecordsCount:0);
        }catch (Exception e){
            LogUtil.error(e);
             return RespBean.error("服务器内部错误！");
        }

    }
    @PostMapping("/addTrainingRecords")
    public RespBean addTrainingRecords(String dept,String team,String jobNumber,String name){
        if(StringUtils.isBlank(dept) || StringUtils.isBlank(team) || StringUtils.isBlank(jobNumber) || StringUtils.isBlank(name)){
            return RespBean.error("必填项不能为空！");
        }
        try{
            trainingRecordsService.addTrainingRecords(UUIDUtil.getUUID(),dept,team,jobNumber,name,new Date());
            return new RespBean(200);
        }catch (Exception e){
            LogUtil.error(e);
            return RespBean.error("服务器内部错误！");
        }
    }
    @Authority("2")
    @PostMapping("/exportTrainingRecords")
    public RespBean exportTrainingRecords(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String dept, String team, String jobNumber, String name,
                                          String startDate, String endDate, int curPageStart, int curPageEnd){
        try{
            trainingRecordsService.exportTrainningRecords(httpServletRequest,httpServletResponse,dept,team,jobNumber,name,startDate,endDate,curPageStart-1,curPageEnd);
            return new RespBean(200);
        }catch (Exception e){
            LogUtil.error(e);
            return RespBean.error("服务器内部错误！");
        }
    }

}
