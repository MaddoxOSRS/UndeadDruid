package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.data.Constants;
import io.maddox.framework.Leaf;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import static io.maddox.data.Areas.CENTER_TILE;
import static io.maddox.data.Areas.NORTHERN_TILE;

public class AvoidMelee extends Leaf {
    @Override
    public boolean isValid() {
        System.out.println("AvoidMelee: "+Configs.avoidMelee());
        return Configs.avoidMelee();
    }

    @Override
    public int onLoop() {
      //  Tile step = Players.local().tile().derive(-1, +2);
            Movement.moveTo(NORTHERN_TILE);
            System.out.println("Deriving Coords");
        return 0;
    }
}
