package com.syy.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syy.common.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author PYY
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2024-04-01 15:15:21
* @Entity generator.domain.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    @Select("select interfaceInfoId, sum(totalNum) as totalNum \n" +
            "from user_interface_info \n" +
            "group by interfaceInfoId \n" +
            "order by totalNum desc \n" +
            "limit #{limit};")
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(@Param("limit") int limit);
}




