package com.pfseven.smdb.domain;

public enum Role {
    ACTOR, DIRECTOR, WRITER, PRODUCER, MUSICIAN;

    public static Role roleCompare(String str){
        if (str.equalsIgnoreCase(ACTOR.toString()))
            return ACTOR;
        else if (str.equalsIgnoreCase(DIRECTOR.toString()))
            return DIRECTOR;
        else if (str.equalsIgnoreCase(WRITER.toString()))
            return WRITER;
        else if (str.equalsIgnoreCase(PRODUCER.toString()))
            return PRODUCER;
        return MUSICIAN;
    }
}
