package io.maddox.behaviour.ReturntoForthos.leafs;

import io.maddox.data.Areas;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class WalktoForthos extends Leaf {
    @Override
    public boolean isValid() {
        return !Areas.FORTHOS_DUNGEON.contains(Players.local()) && !Areas.FORTHOS_ENTRANCE.contains(Players.local());
    }

    @Override
    public int onLoop() {
        Movement.walkTo(Areas.FORTHOS_ENTRANCE.getRandomTile());
        Condition.wait(() -> Areas.FORTHOS_ENTRANCE.contains(Players.local()), 250, 10);
        return 0;
    }
}
