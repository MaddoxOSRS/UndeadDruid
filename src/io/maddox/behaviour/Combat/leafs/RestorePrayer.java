package io.maddox.behaviour.Combat.leafs;


import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.Notifications;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class RestorePrayer extends Leaf {
    @Override
    public boolean isValid() {
        return Configs.restorePray();
    }

    @Override
    public int onLoop() {
        GameObject altaridChange = Objects.stream().within(Areas.ALTAR_ROOM).id(Configs.ALTARS).firstOrNull();
        if (!altaridChange.valid() || !altaridChange.inViewport()) {
            System.out.println("Altar is not found or on-screen, walking to it's location...");
            Camera.turnTo(altaridChange);
            Condition.wait(altaridChange::inViewport, 500, 5);
        }
        if (!altaridChange.interact("Pray-at") || !Condition.wait(() -> Prayer.prayerPoints() == Skill.Prayer.realLevel(), 1250, 10)) {
            Notifications.showNotification("Couldn't Restore Prayer");
            return 0;
        }
        return 0;
    }
}
