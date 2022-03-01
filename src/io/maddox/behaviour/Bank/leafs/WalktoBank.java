package io.maddox.behaviour.Bank.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class WalktoBank extends Leaf {
    @Override
    public boolean isValid() {
        return Areas.OUTSIDE_HOUSE.contains(Players.local()) && !Areas.HOSIDIUS_BANK.contains(Players.local());
    }

    @Override
    public int onLoop() {
            Movement.walkTo(Areas.HOSIDIUS_BANK.getRandomTile());
            Condition.wait(() -> Areas.HOSIDIUS_BANK.contains(Players.local()), 250, 10);

        return 0;
    }
}
