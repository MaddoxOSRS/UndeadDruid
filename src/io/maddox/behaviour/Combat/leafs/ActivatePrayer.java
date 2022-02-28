package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Players;

public class ActivatePrayer extends Leaf {
    @Override
    public boolean isValid() {
        return !Configs.checkifActive() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
        if (Configs.activatePrayer()) {
            System.out.println("Activating Prayers");
            Game.closeOpenTab();
            Condition.wait(Configs::checkifActive, 300, 5);
        }
        return 0;
    }
}
