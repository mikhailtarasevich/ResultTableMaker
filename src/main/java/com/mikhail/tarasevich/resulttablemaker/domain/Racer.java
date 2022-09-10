package com.mikhail.tarasevich.resulttablemaker.domain;

import java.util.Objects;

public class Racer {
    
    private String racerName;
    private String teamName;
    private String startTime;
    private String finishTime;
    public Racer(String racerName, String teamName, String startTime, String finishTime) {
        this.racerName = racerName;
        this.teamName = teamName;
        this.startTime = startTime;
        this.finishTime = finishTime;
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
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
    @Override
    public int hashCode() {
        return Objects.hash(finishTime, racerName, startTime, teamName);
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
        return Objects.equals(finishTime, other.finishTime) && Objects.equals(racerName, other.racerName)
                && Objects.equals(startTime, other.startTime) && Objects.equals(teamName, other.teamName);
    }
    @Override
    public String toString() {
        return "Racer [racerName=" + racerName + ", teamName=" + teamName + ", startTime=" + startTime + ", finishTime="
                + finishTime + "]";
    }
    
       
    
}
