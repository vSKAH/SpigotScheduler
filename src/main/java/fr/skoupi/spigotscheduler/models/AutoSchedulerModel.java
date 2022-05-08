package fr.skoupi.spigotscheduler.models;

/*
 *  * @Created on 04/05/2022 - 18:22
 *  * @Project SpigotScheduler
 *  * @Author Jimmy  / SKAH#7513
 */

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class AutoSchedulerModel {


    private String schedulerName;
    private String schedulerDescription;

    private List<String> schedulerCommand;

    private int schedulerDayInWeek;
    private int schedulerHourInDay;
    private int schedulerMinuteInHour;

    public AutoSchedulerModel() {

    }

    public AutoSchedulerModel(String schedulerName, String schedulerDescription, List<String> schedulerCommand, int schedulerDayInWeek, int schedulerHourInDay, int schedulerMinuteInHour) {
        this.schedulerName = schedulerName;
        this.schedulerDescription = schedulerDescription;
        this.schedulerCommand = schedulerCommand;
        this.schedulerDayInWeek = schedulerDayInWeek;
        this.schedulerHourInDay = schedulerHourInDay;
        this.schedulerMinuteInHour = schedulerMinuteInHour;
    }

    public String getSchedulerName() {
        return schedulerName;
    }

    public void setSchedulerName(String schedulerName) {
        this.schedulerName = schedulerName;
    }

    public String getSchedulerDescription() {
        return schedulerDescription;
    }

    public void setSchedulerDescription(String schedulerDescription) {
        this.schedulerDescription = schedulerDescription;
    }

    public List<String> getSchedulerCommand() {
        return schedulerCommand;
    }

    public void setSchedulerCommand(List<String> schedulerCommand) {
        this.schedulerCommand = schedulerCommand;
    }

    public int getSchedulerDayInWeek() {
        return schedulerDayInWeek;
    }

    public void setSchedulerDayInWeek(int schedulerDayInWeek) {
        this.schedulerDayInWeek = schedulerDayInWeek;
    }

    public int getSchedulerHourInDay() {
        return schedulerHourInDay;
    }

    public void setSchedulerHourInDay(int schedulerHourInDay) {
        this.schedulerHourInDay = schedulerHourInDay;
    }

    public int getSchedulerMinuteInHour() {
        return schedulerMinuteInHour;
    }

    public void setSchedulerMinuteInHour(int schedulerMinuteInHour) {
        this.schedulerMinuteInHour = schedulerMinuteInHour;
    }

    public void buildAutoSchedulerModel(BiConsumer<Calendar, AutoSchedulerModel> callback) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, schedulerDayInWeek);
        calendar.set(Calendar.HOUR_OF_DAY, schedulerHourInDay);
        calendar.set(Calendar.MINUTE, schedulerMinuteInHour);
        calendar.set(Calendar.SECOND, 0);
        if (new Date().after(calendar.getTime())) calendar.add(Calendar.MILLISECOND, Math.toIntExact(TimeUnit.DAYS.toMillis(7)));
        callback.accept(calendar, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutoSchedulerModel)) return false;

        AutoSchedulerModel that = (AutoSchedulerModel) o;

        return getSchedulerName().equals(that.getSchedulerName());
    }

    @Override
    public int hashCode() {
        return getSchedulerName().hashCode();
    }

    @Override
    public String toString() {
        return "AutoSchedulerModel{" +
                "schedulerName='" + schedulerName + '\'' +
                ", schedulerDescription='" + schedulerDescription + '\'' +
                ", schedulerCommand=" + schedulerCommand +
                ", schedulerDayInWeek=" + schedulerDayInWeek +
                ", schedulerHourInDay=" + schedulerHourInDay +
                ", schedulerMinuteInHour=" + schedulerMinuteInHour +
                '}';
    }
}
