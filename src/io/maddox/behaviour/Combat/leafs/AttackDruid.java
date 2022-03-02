package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.data.Constants;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.Notifications;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.mobile.script.ScriptManager;

public class AttackDruid extends Leaf {
    @Override
    public boolean isValid() {
        return !Configs.restorePray() && Configs.checkifActive() && !Configs.interactingwithDruid() && !Configs.getLoot();
    }

    @Override
    public int onLoop() {
        if (!Configs.hasAmmo()) {
            ScriptManager.INSTANCE.stop();
            Notifications.showNotification("No Ammunition Detected, Shutting down.");
            return 0;
        }
        Npc undeadDruid = Configs.nearestDruid();
        if (!undeadDruid.valid() || !undeadDruid.inViewport()) {
            Camera.turnTo(undeadDruid);
            Movement.walkTo(Areas.DRUID_TILE);
            System.out.println("Druid not in viewport.");
            return 0;
        }
        if(!Players.local().interacting().valid()) {
            if (!undeadDruid.interact("Attack") || !Condition.wait(Configs::interactingwithDruid, 1000, 10)) {
                System.out.println("Failed to attack druid, Retrying...");
                return 0;
            }
        }

        return 0;
    }
}
