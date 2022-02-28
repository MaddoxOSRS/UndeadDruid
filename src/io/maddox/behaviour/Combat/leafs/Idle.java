package io.maddox.behaviour.Combat.leafs;

import io.maddox.Main;
import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Players;

public class Idle extends Leaf {
    @Override
    public boolean isValid() {
        return Configs.beingHit() && !Configs.avoidMelee() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
        if (Configs.interactingwithDruid()) {
            Main.lastActivityTime = System.currentTimeMillis();
            if (Players.local().interacting().healthPercent() <= 10) {
                System.out.println("Waiting to not be in combat");
                Condition.wait(() -> Configs.closestItems().valid(), 1500, 5);
            }
        }
        return 0;
    }
}