package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class AvoidMelee extends Leaf {
    @Override
    public boolean isValid() {
        return Configs.avoidMelee();
    }

    @Override
    public int onLoop() {
        Tile step = Players.local().tile().derive(- 1, - 2);
        Movement.step(step);
        return 0;
    }
}
