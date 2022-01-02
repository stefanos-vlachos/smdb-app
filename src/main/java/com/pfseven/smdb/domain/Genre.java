package com.pfseven.smdb.domain;

public enum Genre {
    DRAMA, COMEDY, ANIMATION, THRILLER, ADVENTURE, ROMANCE, WESTERN, CRIME, BIOGRAPHY, ACTION, HISTORY, FANTASY, FAMILY, SCI_FI;

    public static Genre stringCompare(String str){
        return Genre.valueOf((str).toUpperCase().replace('-','_'));
    }

}


