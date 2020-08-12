package com.konglingzhan.manager.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//UserDetails
public class User {
    private Integer id;
    private String username;
    private String telephone;
    private String mail;
    private String password;
    private Integer dept_id;
    private Integer status;
    private String remark;
    private String operator;
    private String operate_ip;
    private Date operate_time;
    private List<Role> roles;

    private Boolean isEnabled = true;

//    @Override
//    @JsonIgnore
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    @JsonIgnore
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    @JsonIgnore
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//
//        return status == 1 ? true : false;
//    }
//
//
//    @Override
//    @JsonIgnore
//    public List<GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        }
//        return authorities;
//    }
}
