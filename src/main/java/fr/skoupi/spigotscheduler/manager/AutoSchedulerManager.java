package fr.skoupi.spigotscheduler.manager;

/*
 *  * @Created on 04/05/2022 - 18:54
 *  * @Project SpigotScheduler
 *  * @Author Jimmy  / SKAH#7513
 */

import fr.skoupi.spigotscheduler.SpigotScheduler;
import fr.skoupi.spigotscheduler.models.AutoSchedulerModel;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class AutoSchedulerManager {


    private static AutoSchedulerManager instance;

    private final SpigotScheduler plugin;

    private final List<AutoSchedulerModel> autoSchedulerList = new ArrayList<>();

    public AutoSchedulerManager(SpigotScheduler plugin) {
        instance = this;
        this.plugin = plugin;
    }

    public void loadSchedulers() {
        autoSchedulerList.clear();

        final FileConfiguration configuration = plugin.getConfig();

        for (String schedulerName : configuration.getConfigurationSection("schedulers").getKeys(false)) {
            AutoSchedulerModel autoSchedulerModel = new AutoSchedulerModel();
            autoSchedulerModel.setSchedulerName(schedulerName);
            autoSchedulerModel.setSchedulerDescription(configuration.getString("schedulers." + schedulerName + ".description"));
            autoSchedulerModel.setSchedulerCommand(configuration.getStringList("schedulers." + schedulerName + ".commands"));
            autoSchedulerModel.setSchedulerDayInWeek(configuration.getInt("schedulers." + schedulerName + ".dayInWeek"));
            autoSchedulerModel.setSchedulerHourInDay(configuration.getInt("schedulers." + schedulerName + ".hourInDay"));
            autoSchedulerModel.setSchedulerMinuteInHour(configuration.getInt("schedulers." + schedulerName + ".minuteInHour"));
            addScheduler(autoSchedulerModel);
        }

    }

    public void addScheduler(AutoSchedulerModel autoSchedulerModel) {
        this.autoSchedulerList.add(autoSchedulerModel);
        plugin.getLogger().info("Scheduler " + autoSchedulerModel.getSchedulerName() + " added");
    }

    public void removeScheduler(String schedulerName) {
        autoSchedulerList.removeIf(autoSchedulerModel -> {
            boolean find = autoSchedulerModel.getSchedulerName().equals(schedulerName);
            if (find) plugin.getLogger().info("Scheduler " + autoSchedulerModel.getSchedulerName() + " removed");
            return find;
        });
    }

    public AutoSchedulerModel getAutoScheduler(String schedulerName) {
        for (AutoSchedulerModel autoScheduler : this.autoSchedulerList) {
            if (autoScheduler.getSchedulerName().equals(schedulerName)) {
                return autoScheduler;
            }
        }
        return null;
    }

    public List<AutoSchedulerModel> getAutoSchedulerList() {
        return autoSchedulerList;
    }

    public static AutoSchedulerManager getInstance() {
        return instance;
    }

}
