package com.soldier.entitys;

public class Subject {

    private Integer subjectId;  //选题主键
    private String teacherId;  //发布教师外键
    private String subjectName;  //选题名称
    private Integer subjectNum;  //发布数量
    private Integer alreadyFillNum;  //已被学生填报的数量
    private String subjectAsk;  //要求

    public Subject(String teacherId, String subjectName, Integer subjectNum,String subjectAsk) {
        this.teacherId = teacherId;
        this.subjectName = subjectName;
        this.subjectNum = subjectNum;
        this.subjectAsk = subjectAsk;
    }

    public Subject(Integer subjectId, String teacherId, String subjectName, Integer subjectNum,String subjectAsk) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.subjectName = subjectName;
        this.subjectNum = subjectNum;
        this.subjectAsk = subjectAsk;
    }

    public Subject(Integer subjectId, String teacherId, String subjectName, Integer subjectNum,Integer alreadyFillNum, String subjectAsk) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.subjectName = subjectName;
        this.subjectNum = subjectNum;
        this.alreadyFillNum = alreadyFillNum;
        this.subjectAsk = subjectAsk;
    }

    public Subject() {
        super();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(Integer subjectNum) {
        this.subjectNum = subjectNum;
    }

    public Integer getAlreadyFillNum() {
        return alreadyFillNum;
    }

    public void setAlreadyFillNum(Integer alreadyFillNum) {
        this.alreadyFillNum = alreadyFillNum;
    }

    public String getSubjectAsk() {
        return subjectAsk;
    }

    public void setSubjectAsk(String subjectAsk) {
        this.subjectAsk = subjectAsk;
    }
}
