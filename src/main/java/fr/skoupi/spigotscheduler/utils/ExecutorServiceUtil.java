package fr.skoupi.spigotscheduler.utils;

/*
 *  * @Created on 04/05/2022 - 19:27
 *  * @Project SpigotScheduler
 *  * @Author Jimmy  / SKAH#7513
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceUtil {


    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();


    public static void execute(Runnable runnable, long initialDelay, long period, TimeUnit timeUnit) {
        getScheduledExecutorService().scheduleWithFixedDelay(runnable, initialDelay, period, timeUnit);
    }

    public static void stopService() {
        getScheduledExecutorService().shutdownNow();
    }

    public static ScheduledExecutorService getScheduledExecutorService() {
        return SCHEDULED_EXECUTOR_SERVICE;
    }
}

