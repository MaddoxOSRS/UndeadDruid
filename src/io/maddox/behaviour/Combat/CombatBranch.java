package io.maddox.behaviour.Combat;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

public class CombatBranch extends Branch {
    @Override
    public boolean isValid() {
        return Configs.restorePray() && Areas.DRUID_ATTACK_AREA.contains(Players.local()) ||
                 !Configs.restorePray() && Configs.checkifActive() && Areas.DRUID_ATTACK_AREA.contains(Players.local()) && !Inventory.isFull()
                || !Configs.checkifActive() && Areas.DRUID_ATTACK_AREA.contains(Players.local())
                || Configs.avoidMelee() && Areas.DRUID_ATTACK_AREA.contains(Players.local())
                || !Inventory.isFull() && Configs.getLoot() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }
}
