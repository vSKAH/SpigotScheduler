package fr.skoupi.spigotscheduler.handler;

/*
 *  * @Created on 04/05/2022 - 18:59
 *  * @Project SpigotScheduler
 *  * @Author Jimmy  / SKAH#7513
 */

import fr.skoupi.spigotscheduler.SpigotScheduler;
import fr.skoupi.spigotscheduler.manager.AutoSchedulerManager;
import fr.skoupi.spigotscheduler.utils.ExecutorServiceUtil;
import org.bukkit.Bukkit;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AutoSchedulerHandler {

    private final SpigotScheduler plugin;

    public AutoSchedulerHandler(SpigotScheduler plugin) {
        this.plugin = plugin;
    }

    public void startSchedulers() {
        AutoSchedulerManager.getInstance().getAutoSchedulerList().forEach(autoSchedulerModel -> startScheduler(autoSchedulerModel.getSchedulerName()));
    }

    public void startScheduler(String schedulerName) {
        AutoSchedulerManager.getInstance().getAutoScheduler(schedulerName).buildAutoSchedulerModel(((calendar, autoSchedulerModel) -> {
            long firstStart = calendar.getTime().getTime() - System.currentTimeMillis();
            long repeatPeriod = TimeUnit.DAYS.toMillis(7);
            ExecutorServiceUtil.execute(() -> autoSchedulerModel.getSchedulerCommand().forEach(cmd -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd)), firstStart, repeatPeriod, TimeUnit.MILLISECONDS);
        }));
    }

    public void stopSchedulers() {
        ExecutorServiceUtil.stopService();
    }


}
