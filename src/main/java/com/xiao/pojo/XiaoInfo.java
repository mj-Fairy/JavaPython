package com.xiao.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class XiaoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     //公司名称
    private String station_name;
     //公司地点
     private String station_addr;
     //公司信息
     private String station_info;
     //职位名称
     private String job_name;
     //职位信息
     private String job_welfare;
     //招聘信息详情页
     private String url;
     //薪水范围
     private String salary;
     //最近发布时间
     private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_addr() {
        return station_addr;
    }

    public void setStation_addr(String station_addr) {
        this.station_addr = station_addr;
    }

    public String getStation_info() {
        return station_info;
    }

    public void setStation_info(String station_info) {
        this.station_info = station_info;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_welfare() {
        return job_welfare;
    }

    public void setJob_welfare(String job_welfare) {
        this.job_welfare = job_welfare;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "XiaoInfo{" +
                "id=" + id +
                ", station_name='" + station_name + '\'' +
                ", station_addr='" + station_addr + '\'' +
                ", station_info='" + station_info + '\'' +
                ", job_name='" + job_name + '\'' +
                ", job_welfare='" + job_welfare + '\'' +
                ", url='" + url + '\'' +
                ", salary='" + salary + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
