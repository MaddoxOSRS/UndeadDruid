package io.maddox.behaviour.Combat;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

public class CombatBranch extends Branch {
    @Override
    public boolean isValid() {
        return Areas.DRUID_ATTACK_AREA.contains(Players.local()) && !Inventory.isFull();
    }
}
