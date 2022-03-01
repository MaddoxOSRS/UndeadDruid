package io.maddox.behaviour.ReturntoForthos.leafs;

import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;

public class EnterForthos  extends Leaf {
    @Override
    public boolean isValid() {
        return Areas.FORTHOS_ENTRANCE.contains(Players.local());
    }

    @Override
    public int onLoop() {
        GameObject forthosentrance = Objects.stream().within(Areas.FORTHOS_ENTRANCE).id(Configs.FORTHOSENTRANCE).firstOrNull();
        if (!forthosentrance.interact("Climb-down") || !Condition.wait(() -> Areas.FORTHOS_DUNGEON.contains(Players.local()), 250, 10)) {
            System.out.println("Failed to enter Fortho Dungeon. Retrying...");
            return 0;
        }
        return 0;
    }
}