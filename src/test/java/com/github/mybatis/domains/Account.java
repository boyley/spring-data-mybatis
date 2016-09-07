package com.github.mybatis.domains;

public class Account {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 国家代码
    */
    private String countryCode;

    /**
    * 手机号
    */
    private String phoneNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}