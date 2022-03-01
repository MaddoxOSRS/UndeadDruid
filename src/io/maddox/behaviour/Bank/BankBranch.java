package io.maddox.behaviour.Bank;

import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;

public class BankBranch extends Branch {
    @Override
    public boolean isValid() {
        return Inventory.isFull()
                || Inventory.stream().name("Teleport to house").isEmpty();
    }
}
