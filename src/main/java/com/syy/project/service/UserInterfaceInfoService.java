package com.syy.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syy.common.model.entity.UserInterfaceInfo;

/**
* @author PYY
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-04-01 15:15:21
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b);

    /**
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId,long userId);
}
