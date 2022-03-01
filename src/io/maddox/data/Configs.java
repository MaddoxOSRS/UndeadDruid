package io.maddox.data;


import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;

public class Configs {

    //Branch information
    public static String currentBranch = "";
    public static String currentLeaf = "";
    public static int loopReturn = 200;
    public static String status = "waiting";
    public static boolean started = false;
    public static boolean startRun = false;
    public static boolean continuetoDruid = false;
    private static int setValue;
    private static int setalchValue;
    private static int setmaxAlchValue;

    //Prayer restoration
    public static final Tile ALTAR_TILE = new Tile(1801, 9951, 0);
    public static final int ALTAR = 34837;
    public static final int[] ALTARS = {34837, 34900};
    public static final String ALTARSTRING = "Altar";
    public static final int ALTARidChange = 34900;
    public static final int FORTHOSENTRANCE = 34865;
    public static boolean restorePray() {
        return Prayer.prayerPoints() < Random.nextInt(15, 35) || Prayer.prayerPoints() == 0;
    }
    // Objects
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
    //Combat
    public static boolean hasAmmo() {
        return Equipment.itemAt(Equipment.Slot.QUIVER).name().contains("bolt") || Equipment.itemAt(Equipment.Slot.QUIVER).name().contains("arrow");
    }
    public static boolean avoidMelee() {
        return Npcs.stream().within(1).name(Configs.DRUIDS_STRING).interactingWithMe().isNotEmpty();
    }
    public static final Tile DRUID_TILE = new Tile(1802, 9942, 0);
    public static Npc nearestDruid() {
        return Npcs.stream().within(Areas.DRUID_ATTACK_AREA).id(Configs.DRUIDS).filtered(n -> !n.interacting().valid() || n.interacting().equals(Players.local())).nearest().first();
    }
    public static boolean beingHit() {
        return Players.local().healthBarVisible();
    }
    public static boolean interactingwithDruid() {
        return Players.local().interacting().getName().equals(Configs.DRUIDS_STRING);
    }    public static boolean nearDead() {
        return Players.local().interacting().healthPercent() <= 10;
    }
    public static final int DRUIDS = 2145;
    public static final String DRUIDS_STRING = "Undead Druid";

    //items
    public static final int dist = 15;
    public static boolean getLoot() {
        return GroundItems.stream().filtered(i -> grounditemPrice(i.id()) >= lootValue()).within(dist).isNotEmpty();
    }
    public static GroundItem closestItems() {
        return GroundItems.stream().filtered(i -> grounditemPrice(i.id()) >= lootValue()).within(dist).nearest().first();
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
        Configs.setValue = setValue;
    }
    public static int lootValue() {
        return setValue;
    }


}
