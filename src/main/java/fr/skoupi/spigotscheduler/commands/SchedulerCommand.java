package fr.skoupi.spigotscheduler.commands;

/*
 *  * @Created on 04/05/2022 - 19:42
 *  * @Project SpigotScheduler
 *  * @Author Jimmy  / SKAH#7513
 */

import fr.skoupi.spigotscheduler.manager.AutoSchedulerManager;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class SchedulerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        AutoSchedulerManager.getInstance().getAutoSchedulerList().forEach(autoScheduler -> {

            sender.sendMessage("§a ");
            sender.sendMessage("§a" + autoScheduler.getSchedulerName() + " §7: §f" + autoScheduler.getSchedulerDescription());
            sender.sendMessage("§aCommands : §f" + autoScheduler.getSchedulerCommand().toString());

            AutoSchedulerManager.getInstance().getAutoScheduler(autoScheduler.getSchedulerName()).buildAutoSchedulerModel(((calendar, autoSchedulerModel) -> {
                long firstStart = calendar.getTime().getTime() - System.currentTimeMillis();
                sender.sendMessage("§aNext Execution in §f" + DurationFormatUtils.formatDuration(firstStart, "d' days 'H' hours 'm' minutes 's' seconds'"));
            }));


        });


        return false;
    }
}
