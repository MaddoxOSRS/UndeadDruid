package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Notifications;
import org.powbot.mobile.script.ScriptManager;

public class NoAmmoShutdown extends Leaf {
    @Override
    public boolean isValid() {
        return !Configs.hasAmmo();
    }

    @Override
    public int onLoop() {
        ScriptManager.INSTANCE.stop();
        Notifications.showNotification("No Ammunition Detected, Shutting down.");
        return 0;
    }
}
