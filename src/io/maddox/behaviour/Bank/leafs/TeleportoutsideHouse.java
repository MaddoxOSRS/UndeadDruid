package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Players;

public class TeleportoutsideHouse extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.isFull() && !Areas.OUTSIDE_HOUSE.contains(Players.local());
    }

    @Override
    public int onLoop() {
        if (Magic.Spell.TELEPORT_TO_HOUSE.cast()) {
            Condition.wait(() -> Areas.OUTSIDE_HOUSE.contains(Players.local()), 1000, 5);
        }
        return 0;
    }
}
