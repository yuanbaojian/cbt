package com.ybj.cbt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private Integer userId;

    private String loginName;

    private String userFullName;

    private String password;

    private String email;

    private String mobilePhone;

    private Integer status;

    private Date createTime;

    private Integer createBy;

    private Integer updatedBy;

    private Date updateTime;

    private Integer failCount;

    private Date lastLoginTime;

    private Integer securityLevel;

    private Integer userType;

    private String gender;

    private String education;

    private Date birthday;

    private String idNo;

    private Integer coachType;

    private Double workYear;

    private String majorType;

    private String company;

    private String remark;

    private Integer deleted;

    private Long orgId;

    private String orgName;

}