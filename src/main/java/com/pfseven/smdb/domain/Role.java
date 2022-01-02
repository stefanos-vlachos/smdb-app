package com.pfseven.smdb.domain;

public enum Role {
    ACTOR, DIRECTOR, WRITER, PRODUCER, MUSICIAN;

    public static Role roleCompare(String str){
        return Role.valueOf((str).toUpperCase().replace('-','_'));
    }
}
