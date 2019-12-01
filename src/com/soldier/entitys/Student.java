package com.soldier.entitys;

public class Student {

    private String studentNo;  //学生主键
    private Integer instituteId;  //学院外键
    private String studentName;  //学生姓名
    private String sex;  //性别
    private Integer politicsType;  //政治面貌：1-党员，2-团员，3-无
    private String email;  //邮箱
    private String tel;  //联系方式
    private Integer firstChooseId;  //第一志愿
    private Integer secondChooseId;  //第二志愿
    private Integer admissionSituation;  //录取情况：1-第一志愿被录取；2-第二志愿被录取
    private Integer score;  //成绩

    public Student(String studentNo, String studentName, String email, String tel, Integer score, Integer firstChooseId, Integer secondChooseId) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.email = email;
        this.tel = tel;
        this.score = score;
        this.firstChooseId = firstChooseId;
        this.secondChooseId = secondChooseId;
    }

    public Student(String studentNo, Integer instituteId, String studentName, String sex, Integer politicsType, String email, String tel) {
        this.studentNo = studentNo;
        this.instituteId = instituteId;
        this.studentName = studentName;
        this.sex = sex;
        this.politicsType = politicsType;
        this.email = email;
        this.tel = tel;
    }

    public Student(String studentNo, Integer instituteId, String studentName, String sex, Integer politicsType, String email, String tel, Integer score) {
        this.studentNo = studentNo;
        this.instituteId = instituteId;
        this.studentName = studentName;
        this.sex = sex;
        this.politicsType = politicsType;
        this.email = email;
        this.tel = tel;
        this.score = score;
    }

    public Student(String studentNo, Integer instituteId, String studentName, String sex, Integer politicsType, String email, String tel, Integer firstChooseId, Integer secondChooseId) {
        this.studentNo = studentNo;
        this.instituteId = instituteId;
        this.studentName = studentName;
        this.sex = sex;
        this.politicsType = politicsType;
        this.email = email;
        this.tel = tel;
        this.firstChooseId = firstChooseId;
        this.secondChooseId = secondChooseId;
    }

    public Student(String studentNo, Integer instituteId, String studentName, String sex, Integer politicsType, String email,
                   String tel, Integer score, Integer firstChooseId, Integer secondChooseId, Integer admissionSituation) {
        this.studentNo = studentNo;
        this.instituteId = instituteId;
        this.studentName = studentName;
        this.sex = sex;
        this.politicsType = politicsType;
        this.email = email;
        this.tel = tel;
        this.score = score;
        this.firstChooseId = firstChooseId;
        this.secondChooseId = secondChooseId;
        this.admissionSituation = admissionSituation;
    }

    public Student() {
        super();
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(Integer politicsType) {
        this.politicsType = politicsType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getFirstChooseId() {
        return firstChooseId;
    }

    public void setFirstChooseId(Integer firstChooseId) {
        this.firstChooseId = firstChooseId;
    }

    public Integer getSecondChooseId() {
        return secondChooseId;
    }

    public void setSecondChooseId(Integer secondChooseId) {
        this.secondChooseId = secondChooseId;
    }

    public Integer getAdmissionSituation() {
        return admissionSituation;
    }

    public void setAdmissionSituation(Integer admissionSituation) {
        this.admissionSituation = admissionSituation;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
