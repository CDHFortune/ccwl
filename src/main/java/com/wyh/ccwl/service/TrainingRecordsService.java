package com.wyh.ccwl.service;

import com.wyh.ccwl.bean.TrainingRecords;
import com.wyh.ccwl.mapper.TrainingRecordsMapper;
import com.wyh.ccwl.util.ExcelExportUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class TrainingRecordsService {
    @Resource
    TrainingRecordsMapper trainingRecordsMapper;
    public List<TrainingRecords> getTrainingRecordsPage(String dept, String team, String jobNumber, String name,
                                                    String startDate, String endDate, Integer page, Integer limit){
        return trainingRecordsMapper.getTrainingRecordsPage(dept,team,jobNumber,name,startDate,endDate,page,limit);
    }

    public List<TrainingRecords> getTrainingRecords(String dept, String team, String jobNumber, String name,
                                                        String startDate, String endDate){
        return trainingRecordsMapper.getTrainingRecords(dept,team,jobNumber,name,startDate,endDate);
    }
    public Integer getTrainingRecordsCount(String dept, String team, String jobNumber, String name,
                                           String startDate, String endDate){
        return trainingRecordsMapper.getTrainingRecordsCount(dept,team,jobNumber,name,startDate,endDate);
    }

    public int addTrainingRecords(String UUID,String dept,String team,String jobNumber,String name,Date ctime){
        return trainingRecordsMapper.addTrainingRecords(UUID,dept,team,jobNumber,name,ctime)  ;
    }

    public void exportTrainningRecords( HttpServletRequest request,HttpServletResponse response,
                                       String dept,String team,String jobNumber,String name, String startDate,String endDate,
                                       int curPageStart,int curPageEnd) throws IOException {
       List<TrainingRecords> trainingRecordsList =  trainingRecordsMapper.getTrainingRecords(dept,team,jobNumber,name,startDate,
               endDate);
        if(trainingRecordsList!=null && trainingRecordsList.size()>0){
            Map<String,String> map = new HashMap<>();
            List<Map> mapList = new ArrayList<>();
            for(int i =0;i<trainingRecordsList.size();i++){
                map.put("id",trainingRecordsList.get(i).getId());
                map.put("dept",trainingRecordsList.get(i).getDept());
                map.put("team",trainingRecordsList.get(i).getTeam());
                map.put("jobnumber",trainingRecordsList.get(i).getJobNumber());
                map.put("name",trainingRecordsList.get(i).getName());
                map.put("ctime",trainingRecordsList.get(i).getCtime().toString());
            }
            String sheetTitle = "sheet1";
            String [] title = new String[]{"ID","部门","班组","工号","名称","提交时间"};        //设置表格表头字段
            String [] properties = new String[]{"id","dept","team","jobnumber","name","ctime"};  // 查询对应的字段
            ExcelExportUtil excelExport2 = new ExcelExportUtil();
            excelExport2.setData(mapList);
            excelExport2.setHeardKey(properties);
            excelExport2.setFontSize(14);
            excelExport2.setSheetName(sheetTitle);
            excelExport2.setTitle(sheetTitle);
            excelExport2.setHeardList(title);
            excelExport2.exportExport(request, response);
        }
    }
}
