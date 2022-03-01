package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.Notifications;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.mobile.script.ScriptManager;

public class InteractwithBank extends Leaf {
    @Override
    public boolean isValid() {
        return Areas.HOSIDIUS_BANK.contains(Players.local());
    }

    @Override
    public int onLoop() {
        if (!Bank.opened()) {
            System.out.println("Bank is closed opening bank...");
            Condition.wait(() -> Bank.open(), 350, 5);
        }
        if (Bank.opened()) {
            if (!Bank.depositInventory() || !Condition.wait(Inventory::isEmpty, 500, 5)) {
                Notifications.showNotification("Couldn't empty inventory");
                return 0;
            }
            if (Configs.outofTabs()) {
                ScriptManager.INSTANCE.stop();
                Notifications.showNotification("No House Tabs in bank, Shutting down.");
            }
            if (Inventory.stream().name("Teleport to house").isEmpty()) {
                if (!Bank.withdraw(8013, 1) || !Condition.wait(Inventory::isNotEmpty, 500, 5)) {
                    Notifications.showNotification("Couldn't withdraw tele tab");
                    return 0;
                }
            }
        }
        return 0;
    }
}
