package fr.skoupi.spigotscheduler;

/*
 *  * @Created on 04/05/2022 - 18:22
 *  * @Project SpigotScheduler
 *  * @Author Jimmy  / SKAH#7513
 */

import fr.skoupi.spigotscheduler.commands.SchedulerCommand;
import fr.skoupi.spigotscheduler.handler.AutoSchedulerHandler;
import fr.skoupi.spigotscheduler.manager.AutoSchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotScheduler extends JavaPlugin {

    private AutoSchedulerHandler schedulerHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new AutoSchedulerManager(this);
        AutoSchedulerManager.getInstance().loadSchedulers();

        schedulerHandler = new AutoSchedulerHandler(this);
        schedulerHandler.startSchedulers();

        Bukkit.getPluginCommand("scheduler").setExecutor(new SchedulerCommand());
        getLogger().info("SpigotScheduler has been enabled!");
    }

    @Override
    public void onDisable() {
        schedulerHandler.stopSchedulers();
        getLogger().info("SpigotScheduler has been disabled!");
        getLogger().info("Bye bye!");
    }
}
