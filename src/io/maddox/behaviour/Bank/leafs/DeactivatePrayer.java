package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Players;

public class DeactivatePrayer extends Leaf {
    @Override
    public boolean isValid() {
        return Areas.OUTSIDE_HOUSE.contains(Players.local()) && Configs.checkifActive();
    }

    @Override
    public int onLoop() {
        if (!Configs.activatePrayer()) {
            System.out.println("Deactivating Prayers");
            Game.closeOpenTab();
            Condition.wait(Configs::checkifDeactive, 300, 5);
        }
        return 0;
    }
}