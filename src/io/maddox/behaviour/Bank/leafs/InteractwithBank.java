package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

public class InteractwithBank extends Leaf {
    @Override
    public boolean isValid() {
        return Areas.HOSIDIUS_BANK.contains(Players.local());
    }

    @Override
    public int onLoop() {
        if (!Bank.opened()) {
            System.out.println("Bank is closed opening bank...");
            Bank.open();
            Condition.wait(() -> Bank.opened(), 350, 5);
        }
        if (Bank.opened()) {
            if (Inventory.isNotEmpty()) {
                Bank.depositInventory();
                Condition.wait(Inventory::isEmpty, 500, 5);
            }
            if (Inventory.stream().name("Teleport to house").isEmpty()) {
                Bank.withdraw(8013, 1);
                Condition.wait(Inventory::isNotEmpty, 500, 5);
            }
        }
        return 0;
    }
}
