package com.syy.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.syy.common.model.entity.InterfaceInfo;
import com.syy.common.model.entity.User;
import com.syy.common.model.entity.UserInterfaceInfo;
import com.syy.project.annotation.AuthCheck;
import com.syy.project.common.*;
import com.syy.project.constant.CommonConstant;
import com.syy.project.exception.BusinessException;
import com.syy.project.mapper.UserInterfaceInfoMapper;
import com.syy.project.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.syy.project.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.syy.project.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.syy.project.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.syy.project.model.enums.InterfaceInfoStatusEnum;
import com.syy.project.model.vo.InterfaceInfoVO;
import com.syy.project.service.InterfaceInfoService;
import com.syy.project.service.UserService;
import com.syy.yyapiclientsdk.client.YyApiClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SYY
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> infoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            interfaceInfoVO.setTotalNum(interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum());
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(infoVOList);

    }

}
