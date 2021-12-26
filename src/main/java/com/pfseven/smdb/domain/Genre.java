package com.pfseven.smdb.domain;

public enum Genre {
    DRAMA, COMEDY, ANIMATION, THRILLER, ADVENTURE, ROMANCE, WESTERN, CRIME, BIOGRAPHY, ACTION, HISTORY;

    public static Genre stringCompare(String str){
        if(str.equalsIgnoreCase(DRAMA.toString().toLowerCase()))
            return DRAMA;
        else if(str.equalsIgnoreCase(COMEDY.toString().toLowerCase()))
            return COMEDY;
        else if(str.equalsIgnoreCase(ANIMATION.toString().toLowerCase()))
            return ANIMATION;
        else if(str.equalsIgnoreCase(THRILLER.toString().toLowerCase()))
            return THRILLER;
        else if(str.equalsIgnoreCase(ADVENTURE.toString().toLowerCase()))
            return ADVENTURE;
        else if(str.equalsIgnoreCase(ROMANCE.toString().toLowerCase()))
            return ROMANCE;
        else if(str.equalsIgnoreCase(WESTERN.toString().toLowerCase()))
            return WESTERN;
        else if(str.equalsIgnoreCase(CRIME.toString().toLowerCase()))
            return CRIME;
        else if(str.equalsIgnoreCase(BIOGRAPHY.toString().toLowerCase()))
            return BIOGRAPHY;
        else if(str.equalsIgnoreCase(ACTION.toString().toLowerCase()))
            return ACTION;
        return HISTORY;
    }

}


