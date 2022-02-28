package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

public class InterferewithBank extends Leaf {
    @Override
    public boolean isValid() {
        return Areas.HOSIDIUS_BANK.contains(Players.local()) && Inventory.isFull();
    }

    @Override
    public int onLoop() {
        if (!Bank.opened()) {
            System.out.println("Bank is closed opening bank...");
            Bank.open();
            Condition.wait(() -> Bank.opened(), 350, 5);
        }
        if (Inventory.isNotEmpty() && Bank.opened()) {
            Bank.depositInventory();
            Condition.wait(Inventory::isEmpty, 500, 5);
        }
        return 0;
    }
}
