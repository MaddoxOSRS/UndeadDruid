package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class AvoidMelee extends Leaf {
    @Override
    public boolean isValid() {
        return Configs.avoidMelee() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
        Tile step = Players.local().tile().derive(+ 2, + 2);
        Movement.step(step);
        return 0;
    }
}
