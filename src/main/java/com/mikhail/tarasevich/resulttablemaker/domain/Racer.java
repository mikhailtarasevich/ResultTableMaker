package com.mikhail.tarasevich.resulttablemaker.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Racer{
    
    private final String racerName;
    private final String teamName;
    private final LocalDateTime startTime;
    private final LocalDateTime finishTime;
   
    private Racer(Builder builder) {
        this.racerName = builder.racerName;
        this.teamName = builder.teamName;
        this.startTime = builder.startTime;
        this.finishTime = builder.finishTime;
    }
    
    public Duration getTimeOfLap() {
        return Duration.between(startTime, finishTime);
    }
    public String getRacerName() {
        return racerName;
    }
    public String getTeamName() {
        return teamName;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getFinishTime() {
        return finishTime;
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

    public static Builder Builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private  String racerName;
        private  String teamName;
        private  LocalDateTime startTime;
        private  LocalDateTime finishTime;
        
        private Builder() {
            
        }
        
        public Builder racerName(final String racerName) {
            this.racerName = racerName;
            return this;
        }
        
        public Builder teamName(final String teamName) {
            this.teamName = teamName;
            return this;
        }
        
        public Builder startTime(final LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }
        
        public Builder finishTime(final LocalDateTime finishTime) {
            this.finishTime = finishTime;
            return this;
        }
        
        public Racer build() {
            return new Racer(this);
        }
        
    } 
    
}
