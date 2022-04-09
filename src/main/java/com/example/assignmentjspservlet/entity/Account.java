package com.example.assignmentjspservlet.entity;

import com.example.assignmentjspservlet.annotation.Column;
import com.example.assignmentjspservlet.annotation.Id;
import com.example.assignmentjspservlet.annotation.Table;

@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "userName", type = "VARCHAR(50)")
    private String userName;
    @Column(name = "fullName", type = "VARCHAR(250)")
    private String fullName;
    @Column(name = "password", type = "TEXT")
    private String password;
    @Column(name = "salt", type = "CHAR(10)")
    private String salt;
    @Column(name = "createdAt", type = "VARCHAR(250)")
    private String createdAt;
    @Column(name = "role", type = "INT")
    private int role;
    @Column(name = "status", type = "INT")
    private int status;

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    public Account() {
    }

    public Account(String userName, String fullName, String password, String salt, String createdAt, int role, int status) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.salt = salt;
        this.createdAt = createdAt;
        this.role = role;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
