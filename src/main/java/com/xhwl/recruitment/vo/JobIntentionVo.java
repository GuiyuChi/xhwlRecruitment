package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:33 2018/4/16
 **/
public class JobIntentionVo {
    private long id;
    private String workPlace;
    private Integer salary;
    private String expectedTimeForDuty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getExpectedTimeForDuty() {
        return expectedTimeForDuty;
    }

    public void setExpectedTimeForDuty(String expectedTimeForDuty) {
        this.expectedTimeForDuty = expectedTimeForDuty;
    }

    @Override
    public String toString() {
        return "JobIntensionVo{" +
                "id=" + id +
                ", workPlace='" + workPlace + '\'' +
                ", salary=" + salary +
                ", expectedTimeForDuty='" + expectedTimeForDuty + '\'' +
                '}';
    }
}
