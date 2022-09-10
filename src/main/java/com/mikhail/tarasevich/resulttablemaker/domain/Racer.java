package com.mikhail.tarasevich.resulttablemaker.domain;

public class Racer {
    private String racerAbbreviation;
    private String racerName;
    private String teamName;
    
    public Racer(String abbreviation, String racerName, String teamName) {
        this.racerAbbreviation = abbreviation;
        this.racerName = racerName;
        this.teamName = teamName;
    }

    public String getAbbreviation() {
        return racerAbbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.racerAbbreviation = abbreviation;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((racerAbbreviation == null) ? 0 : racerAbbreviation.hashCode());
        result = prime * result + ((racerName == null) ? 0 : racerName.hashCode());
        result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
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
        Racer other = (Racer) obj;
        if (racerAbbreviation == null) {
            if (other.racerAbbreviation != null)
                return false;
        } else if (!racerAbbreviation.equals(other.racerAbbreviation))
            return false;
        if (racerName == null) {
            if (other.racerName != null)
                return false;
        } else if (!racerName.equals(other.racerName))
            return false;
        if (teamName == null) {
            if (other.teamName != null)
                return false;
        } else if (!teamName.equals(other.teamName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Racer [abbreviation=" + racerAbbreviation + ", racerName=" + racerName + ", teamName=" + teamName + "]";
    }
    
    
}
