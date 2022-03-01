package io.maddox;

import com.google.common.eventbus.Subscribe;
import io.maddox.behaviour.Bank.BankBranch;
import io.maddox.behaviour.Bank.leafs.*;
import io.maddox.behaviour.Combat.CombatBranch;
import io.maddox.behaviour.Combat.leafs.*;
import io.maddox.behaviour.ReturntoForthos.ForthosBranch;
import io.maddox.behaviour.ReturntoForthos.leafs.EnterForthos;
import io.maddox.behaviour.ReturntoForthos.leafs.WalktoDruids;
import io.maddox.behaviour.ReturntoForthos.leafs.WalktoForthos;
import io.maddox.behaviour.fallback.FallbackLeaf;
import io.maddox.behaviour.timeout.TimeoutLeaf;
import io.maddox.data.Areas;
import io.maddox.data.Configs;
import io.maddox.framework.Tree;
import org.powbot.api.event.BreakEvent;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;


/**
 * Credits to @Proto && @Dan && @BPL for Guidance, and information, Credits to Powbot Development Discord Section
 */



@ScriptManifest(
        name = "Maddox's Undead Druids",
        description = "Kills Undead Druis in Forthos dungeon.",
        version = "0.01",
        author = "Maddox"
)
@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name = "Grab Items above GP Value:",
                        description = "Please enter the gp value you would like to loot.",
                        optionType = OptionType.INTEGER,
                        defaultValue = "1000"
                )
        }
)


public class Main extends AbstractScript {

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("Maddox's Undead Druids", "low", "127.0.0.1:5575", true, false);
    }
    private final Tree tree = new Tree();
    public static long lastActivityTime = 0;
    @Override
    public void onStart() {
        int lootValue = getOption("Grab Items above GP Value:");
        Configs.setlootValue(lootValue);
        Paint p = new PaintBuilder()
                .addString("Leaf:" , () -> Configs.currentLeaf )
                .trackSkill(Skill.Ranged)
                .trackInventoryItems(23499, 995, 560, 565, 557, 1393, 1395, 1397, 1399, 562, 561, 4698, 564, 207, 5295, 5300, 3138, 245, 1249, 2366, 1247, 217)
                .x(30)
                .y(50)
                .build();
        addPaint(p);
        instantiateTree();
    }

    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),
                new CombatBranch().addLeafs(new RestorePrayer(), new AttackDruid(), new ActivatePrayer(), new Idle(), new LootItems(), new AvoidMelee()),
                new BankBranch().addLeafs(new TeleportoutsideHouse(), new WalktoBank(), new InteractwithBank(), new DeactivatePrayer(), new NoAmmoShutdown()),
                new ForthosBranch().addLeafs(new EnterForthos(), new WalktoDruids(), new WalktoForthos()),
                new TimeoutLeaf(),
                new FallbackLeaf());
    }

    @Override
    public void poll() {
        if(Areas.LUMBRIDGE.contains(Players.local())) {
            ScriptManager.INSTANCE.stop();
        }

        tree.onLoop();
    }


 @Subscribe
    public void onBreak(BreakEvent e) {
        if(Areas.HOSIDIUS_BANK.contains(Players.local())) {
            e.delay(10000);
        }
    }
}
