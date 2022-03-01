package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.mobile.script.ScriptManager;

public class TeleportoutsideHouse extends Leaf {
    @Override
    public boolean isValid() {
        return !Areas.OUTSIDE_HOUSE.contains(Players.local());
    }

    @Override
    public int onLoop() {
        if (Inventory.stream().name("Teleport to house").isEmpty()) {
            ScriptManager.INSTANCE.stop();
        }
        if (Game.tab(Game.Tab.INVENTORY)) {
            Item housetablet = Inventory.stream().name("Teleport to house").first();
            if (!housetablet.interact("Outside") || !Condition.wait(() -> Areas.OUTSIDE_HOUSE.contains(Players.local()), 1000, 5)) {
                System.out.println("Failed to teleport outside of house, Retrying");
                return 0;
            }
        }
        return 0;
    }
}
