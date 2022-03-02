package io.maddox.behaviour.ReturntoForthos.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class WalktoDruids extends Leaf {
    @Override
    public boolean isValid() {
        return  Areas.FORTHOS_DUNGEON.contains(Players.local()) && !Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
        Movement.walkTo(Areas.DRUID_AREA.getRandomTile());
        Condition.wait(() -> Areas.DRUID_ATTACK_AREA.contains(Players.local()), 250, 5);
        return 0;
    }
}
