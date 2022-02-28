package io.maddox.behaviour.ReturntoForthos;

import io.maddox.data.Areas;
import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

public class ForthosBranch extends Branch {
    @Override
    public boolean isValid() {
        return !Inventory.isFull() && !Areas.FORTHOS_DUNGEON.contains(Players.local())
                || !Inventory.isFull() && Areas.FORTHOS_DUNGEON.contains(Players.local()) && !Areas.DRUID_AREA.contains(Players.local());
    }
}
