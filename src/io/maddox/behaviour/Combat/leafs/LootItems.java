package io.maddox.behaviour.Combat.leafs;

import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.Inventory;

public class LootItems extends Leaf {
    @Override
    public boolean isValid() {
        return !Inventory.isFull() && Configs.getLoot();
    }

    @Override
    public int onLoop() {
        GroundItem item = Configs.closestItems();

        if (!item.valid() && !item.inViewport()) {
            Camera.turnTo(item);
            System.out.println("Turning Camera to Loot");
            return 0;
        }
            if (!item.interact("Take") || !Condition.wait(() -> !Configs.getLoot(), 250, 5)) {
                System.out.println("Failed to loot, Retrying...");
                return 0;
        }
        return 0;
    }
}
