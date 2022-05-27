package com.example.demo.user;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@ToString
@Entity
@Data
@Table
public class UserEntity {


    @Column
    private String email;
}
