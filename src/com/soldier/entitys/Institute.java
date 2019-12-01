package com.soldier.entitys;

/**
 *
 */
public class Institute {

    private Integer instituteId;  //学院主键
    private String instituteName;  //学院名称

    public Integer getInstituteId() {
        return instituteId;
    }
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }
    public String getInstituteName() {
        return instituteName;
    }
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public Institute() {
        super();
    }

    public Institute(Integer instituteId, String instituteName) {
        this.instituteId = instituteId;
        this.instituteName = instituteName;
    }
}
