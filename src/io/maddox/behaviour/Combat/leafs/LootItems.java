package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

public class LootItems extends Leaf {
    @Override
    public boolean isValid() {
        return !Inventory.isFull() && Configs.getLoot() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
        GroundItem item = Configs.closestItems();

        if (!item.valid() && !item.inViewport()) {
            System.out.println("Loot items");
            return 0;
        }
            if (item.interact("Take")) {
                Condition.wait(() -> !Configs.getLoot(), 250, 5);
        }
        return 0;
    }
}
