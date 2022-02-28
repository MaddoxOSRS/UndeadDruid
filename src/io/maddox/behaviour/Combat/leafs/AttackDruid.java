package io.maddox.behaviour.Combat.leafs;

import io.maddox.Main;
import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class AttackDruid extends Leaf {
    @Override
    public boolean isValid() {
        return !Configs.restorePray() && Configs.checkifActive() && !Configs.interactingwithDruid() && !Configs.getLoot() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
        Npc undeadDruid = Configs.nearestDruid();
        if (!undeadDruid.valid() || !undeadDruid.inViewport()) {
            Movement.walkTo(Configs.DRUID_TILE);
            System.out.println("Druid not in viewport.");
            return 0;
        }
        if(!Players.local().interacting().valid()) {
            if (undeadDruid.interact("Attack")) {
                System.out.println("Attacking Druid...");
                Condition.wait(Configs::interactingwithDruid, 1000, 10);
            }
        }

        return 0;
    }
}
