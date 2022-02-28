package io.maddox.behaviour.Combat.leafs;


import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class RestorePrayer extends Leaf {
    @Override
    public boolean isValid() {
        return Configs.restorePray() && Areas.DRUID_ATTACK_AREA.contains(Players.local());
    }

    @Override
    public int onLoop() {
       // GameObject altar = Objects.stream().within(Areas.ALTAR_ROOM).id(Configs.ALTAR).firstOrNull();
        GameObject altaridChange = Objects.stream().within(Areas.ALTAR_ROOM).id(Configs.ALTARidChange).firstOrNull();
        if (!altaridChange.valid() || !altaridChange.inViewport()) {
            System.out.println("Altar is not found or on-screen, walking to it's location...");
            Movement.walkTo(Configs.ALTAR_TILE);
            Condition.wait(altaridChange::inViewport, 500, 5);
        }
        if (altaridChange.interact("Pray-at")) {
            System.out.println("Interacting with Altar...");
            Condition.wait(() -> Prayer.prayerPoints() == Skill.Prayer.realLevel(), 700, 5);
        }
        return 0;
    }
}
