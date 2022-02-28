package io.maddox.behaviour.ReturntoForthos;

import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;

public class ForthosBranch extends Branch {
    @Override
    public boolean isValid() {
        return Inventory.stream().name("Teleport to house").isNotEmpty();
    }
}
