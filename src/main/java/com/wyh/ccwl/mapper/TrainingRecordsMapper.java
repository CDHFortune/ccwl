package com.wyh.ccwl.mapper;

import com.wyh.ccwl.bean.TrainingRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface TrainingRecordsMapper {
    public List<TrainingRecords> getTrainingRecordsPage(@Param("dept") String dept,@Param("team") String team,@Param("jobNumber") String jobNumber,
                                                    @Param("name") String name,@Param("startDate") String startDate,@Param("endDate") String endDate,
                                                    @Param("page") Integer page,@Param("limit") Integer limit);
    public List<TrainingRecords> getTrainingRecords(@Param("dept") String dept,@Param("team") String team,@Param("jobNumber") String jobNumber,
                                                    @Param("name") String name,@Param("startDate") String startDate,@Param("endDate") String endDate);
    public Integer getTrainingRecordsCount(@Param("dept") String dept,@Param("team") String team,@Param("jobNumber") String jobNumber,
                                           @Param("name") String name,@Param("startDate") String startDate,@Param("endDate") String endDate);
    public Integer addTrainingRecords(@Param("UUID") String UUID,@Param("dept") String dept,@Param("team") String team,@Param("jobNumber")String jobNumber,@Param("name")String name,
                                      @Param("ctime") Date ctime);
}
