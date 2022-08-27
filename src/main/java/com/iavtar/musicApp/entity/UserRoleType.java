package com.iavtar.musicApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleType {

    ADMIN("admin"),
    USER("user");

    public String name;

}
