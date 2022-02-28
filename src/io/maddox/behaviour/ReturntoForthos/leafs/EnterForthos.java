package io.maddox.behaviour.ReturntoForthos.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class EnterForthos  extends Leaf {
    @Override
    public boolean isValid() {
        return !Inventory.isFull() && Areas.FORTHOS_ENTRANCE.contains(Players.local());
    }

    @Override
    public int onLoop() {
        GameObject forthosentrance = Objects.stream().within(Areas.FORTHOS_ENTRANCE).id(Configs.FORTHOSENTRANCE).firstOrNull();
        if (forthosentrance.interact("Climb-down")) {
            System.out.println("Entering Forthos Dungeon...");
            Condition.wait(() -> Areas.FORTHOS_DUNGEON.contains(Players.local()), 250, 10);
        }
        return 0;
    }
}