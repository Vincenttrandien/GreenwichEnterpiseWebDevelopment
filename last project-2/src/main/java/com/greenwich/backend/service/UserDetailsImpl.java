package com.greenwich.backend.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenwich.backend.entity.User;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private ObjectId id;

    private String codeUser;
    private String name;
    private String email;
    private String dob;
    private String address;
    private String phoneNumber;
    private String nam;

    private String facultyName;
    private String username;

    @JsonIgnore
    private String password;

    private String retypePassword;

    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(ObjectId id, String codeUser , String name ,
                           String email, String dob, String address, String phoneNumber ,
                           String nam,String facultyName,String username, String password, String retypePassword,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.codeUser = codeUser;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nam = nam;
        this.facultyName= facultyName;
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getCodeUser(),
                user.getName(),
                user.getEmail(),
                user.getDob(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getNam(),
                user.getFacultyName(),
                user.getUsername(),
                user.getPassword(),
                user.getRetypePassword(),
                authorities);
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public ObjectId getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
