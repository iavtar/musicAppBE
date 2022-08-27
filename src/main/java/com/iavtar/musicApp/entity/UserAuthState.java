package com.iavtar.musicApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAuthState {

    ACTIVE("active"),
    DE_ACTIVATED("de_activated"),
    BLACKLISTED("blacklisted");

    public String name;

}
