package com.mikhail.tarasevich.resulttablemaker.domain;

public class TimeStamp {
    
    private String racerAbbreviation;
    private String time;
    
    public TimeStamp(String racerAbbreviation, String time) {
        this.racerAbbreviation = racerAbbreviation;
        this.time = time;
    }

    public String getRacerAbbreviation() {
        return racerAbbreviation;
    }

    public void setRacerAbbreviation(String racerAbbreviation) {
        this.racerAbbreviation = racerAbbreviation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((racerAbbreviation == null) ? 0 : racerAbbreviation.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeStamp other = (TimeStamp) obj;
        if (racerAbbreviation == null) {
            if (other.racerAbbreviation != null)
                return false;
        } else if (!racerAbbreviation.equals(other.racerAbbreviation))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TimeStamp [racerAbbreviation=" + racerAbbreviation + ", time=" + time + "]";
    }   
    
}
