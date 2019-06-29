package com.xiao.service;

import com.xiao.dao.XiaoInfoDao;
import com.xiao.pojo.XiaoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XiaoInfoServiceImpl implements XiaoInfoService {
    @Autowired
    private XiaoInfoDao xiaoInfoDao;

    @Override
    @Transactional
    public void save(XiaoInfo xiaoInfo) {
        //先从数据库查询数据，根据URL和日期查询
        XiaoInfo info=new XiaoInfo();
        info.setUrl(xiaoInfo.getUrl());
        info.setTime(xiaoInfo.getTime());
        //将查询数据添加到List集合里面
        List<XiaoInfo> list=this.findXiaoInfo(info);

        if(list.size()==0){
            //没有查询到数据则新增或者修改数据
            this.xiaoInfoDao.saveAndFlush(xiaoInfo);
        }
    }

    @Override
    public List<XiaoInfo> findXiaoInfo(XiaoInfo xiaoInfo) {
        //设置查询条件
        Example example=Example.of(xiaoInfo);
        //执行查询
        List list = this.xiaoInfoDao.findAll(example);
        return list;
    }

    @Override
    public Page<XiaoInfo> findXiaoInfoByPage(int page, int rows) {
        Page<XiaoInfo> xiaoInfos=this.xiaoInfoDao.findAll(PageRequest.of(page-1,rows));
        return xiaoInfos;
    }
}
