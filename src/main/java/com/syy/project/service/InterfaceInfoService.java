package com.syy.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syy.common.model.entity.InterfaceInfo;

/**
* @author SYY
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-02-28 16:46:23
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
