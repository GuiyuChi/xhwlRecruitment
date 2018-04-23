package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午12:15 2018/4/15
 **/
public class AwardVo {
    private Long id;
    private String awardName;
    private String dateOfAward;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getDateOfAward() {
        return dateOfAward;
    }

    public void setDateOfAward(String dateOfAward) {
        this.dateOfAward = dateOfAward;
    }

    @Override
    public String toString() {
        return "AwardVo{" +
                "id=" + id +
                ", awardName='" + awardName + '\'' +
                ", dateOfAward='" + dateOfAward + '\'' +
                '}';
    }
}
