package com.soldier.entitys;

public class Teacher {

    private String teacherId;  //教师主键
    private Integer titleId;  //职称外键
    private Integer instituteId;  //所属学院外键
    private String teacherName;  //姓名
    private String sex;  //性别
    private String tel;
    private String email;
    private Integer mySubjectNum;  //个人已发布选题数

    public Teacher(String teacherId, Integer titleId, Integer instituteId, String teacherName, String sex, String tel, String email) {
        this.teacherId = teacherId;
        this.titleId = titleId;
        this.instituteId = instituteId;
        this.teacherName = teacherName;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
    }

    public Teacher(String teacherId, Integer titleId, Integer instituteId, String teacherName, String sex, String tel, String email, Integer mySubjectNum) {
        this.teacherId = teacherId;
        this.titleId = titleId;
        this.instituteId = instituteId;
        this.teacherName = teacherName;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
        this.mySubjectNum = mySubjectNum;
    }

    public Teacher() {
        super();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMySubjectNum() {
        return mySubjectNum;
    }

    public void setMySubjectNum(Integer mySubjectNum) {
        this.mySubjectNum = mySubjectNum;
    }
}
