package com.xiao.pipeline;

import com.xiao.pojo.XiaoInfo;
import com.xiao.service.XiaoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class SpringDataPipeline implements Pipeline,us.codecraft.webmagic.pipeline.Pipeline{

    @Autowired
    private XiaoInfoService xiaoInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取要保存的MySql的数据
        XiaoInfo xiaoInfo=resultItems.get("xiaoInfo");
        //判断获取到的数据不为空
        if(xiaoInfo!=null){
            //如果有值则进行保存
            this.xiaoInfoService.save(xiaoInfo);
        }
    }
}
