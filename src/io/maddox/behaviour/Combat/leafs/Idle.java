package io.maddox.behaviour.Combat.leafs;

import io.maddox.Main;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Players;

public class Idle extends Leaf {
    @Override
    public boolean isValid() {
        return Configs.beingHit();
    }

    @Override
    public int onLoop() {
        if (Configs.interactingwithDruid()) {
            Main.lastActivityTime = System.currentTimeMillis();
            if (Configs.nearDead()) {
                System.out.println("Waiting to not be in combat");
                Condition.wait(() -> Configs.closestItems().valid(), 500, 5);
            }
        }
        return 0;
    }
}
