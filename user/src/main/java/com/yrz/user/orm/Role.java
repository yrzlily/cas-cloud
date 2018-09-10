package com.yrz.user.orm;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author yrz
 */
@Entity
@DynamicUpdate
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_mark")
    private String roleMark;

    @JsonIgnoreProperties(value = "roles")
    @ManyToMany
    @JoinTable(name = "UserRole" , joinColumns = {@JoinColumn(name="role_id")} , inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMark() {
        return roleMark;
    }

    public void setRoleMark(String roleMark) {
        this.roleMark = roleMark;
    }

    public List<User> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<User> userInfo) {
        this.userInfo = userInfo;
    }
}
