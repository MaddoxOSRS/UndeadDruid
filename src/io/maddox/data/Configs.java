package io.maddox.data;


import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;

import static io.maddox.data.Constants.dist;
import static io.maddox.data.Constants.setValue;

public class Configs {


    //Prayer boolean
    public static boolean restorePray() {
        return Prayer.prayerPoints() < Random.nextInt(15, 35) || Prayer.prayerPoints() == 0;
    }
    //Using prayers
    public static boolean checkifActive(){
        return Prayer.prayerActive(Prayer.Effect.PROTECT_FROM_MAGIC);
    }
    public static boolean checkifDeactive(){
        return !Prayer.prayerActive(Prayer.Effect.PROTECT_FROM_MAGIC);
    }
    public static boolean activatePrayer(){
        return Prayer.prayer(Prayer.Effect.PROTECT_FROM_MAGIC, true);
    }
    public static boolean deactivatePrayer(){
        return Prayer.prayer(Prayer.Effect.PROTECT_FROM_MAGIC, false);
    }
    public static boolean beingHit() {
        return Players.local().healthBarVisible();
    }
    public static boolean interactingwithDruid() {
        return Players.local().interacting().getName().equals(Constants.DRUIDS_STRING);
    }    public static boolean nearDead() {
        return Players.local().interacting().healthPercent() <= 10;
    }
    public static int grounditemPrice(int itemID) {
        GroundItem item = GroundItems.stream().id(itemID).first();
        if (item != null && item.valid()) {
            if (item.noted()) {
                return GrandExchange.getItemPrice(item.id() - 1)*item.stackSize();
            } else
                return GrandExchange.getItemPrice(item.id())*item.stackSize();
        }
        return 0;
    }
    public static void setlootValue(int setValue) {
        Constants.setValue = setValue;
    }
    public static int lootValue() {
        return setValue;
    }

    //Combat
    public static boolean hasAmmo() {
        return Equipment.itemAt(Equipment.Slot.QUIVER).id() != Constants.RuneArrowID;
    }
    public static boolean outofTabs() {
        return Bank.stream().id(8013).isEmpty();
    }
    public static boolean avoidMelee() {
        return Npcs.stream().within(1).id(Constants.DRUIDS).interactingWithMe().isNotEmpty();
    }
    public static Npc nearestDruid() {
        return Npcs.stream().within(Areas.DRUID_ATTACK_AREA).id(Constants.DRUIDS).filtered(n -> !n.interacting().valid() || n.interacting().equals(Players.local())).nearest().first();
    }
    //items
    public static boolean getLoot() {
        return GroundItems.stream().filtered(i -> grounditemPrice(i.id()) >= lootValue()).within(dist).isNotEmpty();
    }
    public static GroundItem closestItems() {
        return GroundItems.stream().filtered(i -> grounditemPrice(i.id()) >= lootValue()).within(dist).nearest().first();
    }

}
