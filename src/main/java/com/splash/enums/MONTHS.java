package com.splash.enums;

public enum MONTHS {
    Jan(0),
    Feb(1),
    Mar(2),
    Apr(3),
    May(4),
    Jun(5),
    Jul(6),
    Aug(7),
    Sep(8),
    Oct(9),
    Nov(10),
    Dec(11),;

    private final int index;
    MONTHS( int index){
        this.index=index;
    }
    public static Integer getIndex(String month){
        for (MONTHS mon:
             MONTHS.values()) {
            if(mon.name().equals(month)){
                return mon.index;
            }
        }
        return null;

    }
}
