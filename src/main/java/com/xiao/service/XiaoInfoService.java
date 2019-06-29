package com.xiao.service;

import com.xiao.pojo.XiaoInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface XiaoInfoService {

    /**
     * 保存工作数据
     *
     */
    public void save(XiaoInfo xiaoInfo);


    /**
     * 根据条件查询工作信息
     * @return
     */
    public List<XiaoInfo> findXiaoInfo(XiaoInfo xiaoInfo);

    /**
     * 分页查询数据
     * @param page
     * @param rows
     * @return
     */
    Page<XiaoInfo> findXiaoInfoByPage(int page, int rows);

}
