package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Configs;
import io.maddox.data.Constants;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Game;

public class ActivatePrayer extends Leaf {
    @Override
    public boolean isValid() {
        return !Configs.checkifActive();
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
