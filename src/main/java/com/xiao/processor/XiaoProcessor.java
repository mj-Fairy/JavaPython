package com.xiao.processor;

import com.xiao.pipeline.SpringDataPipeline;
import com.xiao.pojo.XiaoInfo;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;


@Component
public class XiaoProcessor implements PageProcessor {

    private String url="http://www.58xiaoyuan.com/index.php/Item/index.html";

     @Override
    public void process(Page page) {
        //解析页面，获取招聘信息详情的url地址
        List<Selectable> list=page.getHtml().css("ul#list_con li div div a").nodes();
        //判断获取到的集合是否为空
        if(list.size()==0){
            this.saveXiaoInfo(page);
        }else {
            //如果不为空，表示这是列表页,解析出详情页的url地址，放到任务队列中
            for (Selectable selectable: list){
                //获取url地址
                String xiaoInfoUrl=selectable.links().toString();
                //把获取到的url地址放到任务对列中
                page.addTargetRequest(xiaoInfoUrl);
            }
            //获取下一页url
            String bkUrl=page.getHtml().css("div.pagination ul li:eq(1)").nodes().get(0).links().toString();
            //把url放入任务队列中
            page.addTargetRequest(bkUrl);
        }
        String html=page.getHtml().toString();
        System.out.println("=================可爱的分割线==================");
    }

    //解析页面，获取招聘详情信息，保存数据
    private void saveXiaoInfo(Page page){
        //创建招聘详情对象
        XiaoInfo xiaoInfo =new XiaoInfo();
        //解析页面
        Html html=page.getHtml();

        //获取数据封装到对象中
        /*
         * id  主键
         * station_name  公司名称
         * station_addr  公司地点
         * station_info  公司信息
         * job_name  职位名称
         * job_welfare  职位信息
         * url  招聘信息详情页
         * salary  薪水范围
         * time  最近发布时间
         */
        xiaoInfo.setStation_name(html.css("div.comp_baseInfo_title div.baseInfo_link a","text").toString());
        xiaoInfo.setStation_info(html.css("div.com_identify div.identify_title span","text").toString());
        xiaoInfo.setJob_name(html.css("div.pos_base_info span.pos_title","text").toString());
        xiaoInfo.setStation_addr(html.css("span#mapAddr","text").toString());
        xiaoInfo.setJob_welfare(html.css("div.posDes div.des","text").toString());
        xiaoInfo.setUrl(page.getUrl().toString());
        xiaoInfo.setSalary(html.css("div.pos_base_info span.pos_salary","text").toString());
        xiaoInfo.setTime(html.css("div.pos_base_statistics span " +
                "span strong","text").toString());
        //xiaoInfo.setTime(Jsoup.parse(html.css("spen.pos_base_num span strong").toString()).text());
        // Jsoup.parse(html.css("spen.pos_base_num span strong").toString()).text()
        //把结果保存出来
        page.putField("xiaoInfo",xiaoInfo);
    }
    private Site site = Site.me()
            .setCharset("utf-8")//设置编码
            .setTimeOut(10 * 1000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setRetryTimes(3);//设置重试的次数

    @Override
    public Site getSite() {
        return site;
    }
    @Autowired
    private SpringDataPipeline springDataPipeline;

    //initialDelay当任务启动后，等等多久执行方法
    //fixedDelay每个多久执行方法
    @Scheduled(initialDelay = 1000, fixedDelay = 100 * 1000)
    public void Process(){
        Spider.create(new XiaoProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .addPipeline(this.springDataPipeline)
                .run();
    }

}
