package com.syy.project.model.vo;

import com.syy.common.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口信息封装视图
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo {
    /**
     * 调用总次数
     */
    private Integer totalNum;
}