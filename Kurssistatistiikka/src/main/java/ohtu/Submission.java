package ohtu;

import java.util.List;

public class Submission {

    private int week;
    private int hours;
    private List<Integer> exercises;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

        @Override
    public String toString() {
        return "viikko " + week + ": \n  "
                + "tehtyjä tehtäviä yhteensä: " + this.getExercises().size() + ", "
                + "aikaa kului yhteensä: " + this.getHours() + " tuntia, " 
                + "tehdyt tehtävät: " + this.getExercises();
    }
}
