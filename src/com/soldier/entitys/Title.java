package com.soldier.entitys;

public class Title {

    private Integer titleId;  //职称ID
    private String titleName;  //职称名

    public Title(Integer titleId, String titleName) {
        this.titleId = titleId;
        this.titleName = titleName;
    }

    public Title() {
        super();
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}
