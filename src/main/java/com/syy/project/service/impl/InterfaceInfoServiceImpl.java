package com.syy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syy.common.model.entity.InterfaceInfo;
import com.syy.project.common.ErrorCode;
import com.syy.project.exception.BusinessException;
import com.syy.project.mapper.InterfaceInfoMapper;
import com.syy.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author SYY
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2024-02-28 16:46:23
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        String method = interfaceInfo.getMethod();
        // 创建时，所有参数必须非空
        if (StringUtils.isAnyBlank(name, description, url, requestHeader, responseHeader, method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }
}




