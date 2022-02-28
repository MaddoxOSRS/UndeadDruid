package io.maddox.behaviour.ReturntoForthos.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class WalktoForthos extends Leaf {
    @Override
    public boolean isValid() {
        return !Inventory.isFull();
    }

    @Override
    public int onLoop() {
        Movement.walkTo(Areas.FORTHOS_ENTRANCE.getRandomTile());
        Condition.wait(() -> Players.local().inMotion(), 250, 5);
        return 0;
    }
}
