package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.data.Constants;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;

public class TeleportoutsideHouse extends Leaf {
    @Override
    public boolean isValid() {
        return !Areas.OUTSIDE_HOUSE.contains(Players.local()) && Inventory.stream().name("Teleport to house").isNotEmpty();
    }

    @Override
    public int onLoop() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            Item housetablet = Inventory.stream().name("Teleport to house").first();
            if (!housetablet.interact("Outside") || !Condition.wait(() -> Areas.OUTSIDE_HOUSE.contains(Players.local()), 1000, 5)) {
                System.out.println("Failed to teleport outside of house, Retrying");
                return 0;
            }
            if (!Configs.deactivatePrayer() || !Condition.wait(Configs::checkifDeactive, 300, 5)) {
                System.out.println("Deactivating Prayers");
                return 0;
            }
        }
        return 0;
    }
}
