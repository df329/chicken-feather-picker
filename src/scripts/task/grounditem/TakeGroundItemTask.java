package scripts.task.grounditem;

import scripts.task.*;
import scripts.util.*;

import org.powerbot.script.Area;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

/**
 * Take ground item (chicken feather) task.
 */
public class TakeGroundItemTask extends Task<ClientContext> {
    private static int chickenFeatherId = 314;
    private static int playerIdle = -1;

    public TakeGroundItemTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        boolean chickenFeathersExistInInventory = ctx.inventory.select().id(chickenFeatherId).count(true) > 0;

        // Full inventory with feather is okay
        if (ctx.inventory.isFull() && !chickenFeathersExistInInventory) {
            System.out.println("Inventory full: dropping unwanted chicken loot.");
            DropItemsUtil.DropAllUnwantedChickenLoot(ctx);
        }

        // Inventory is not full, chicken feather exists and player is idle
        return (!ctx.inventory.isFull() || chickenFeathersExistInInventory)
            && !ctx.groundItems.select().id(chickenFeatherId).isEmpty()
            && ctx.players.local().animation() == playerIdle;
    }

    @Override
    public void execute(Area area) {
        GroundItem chickenFeather = ctx.groundItems.within(area).nearest().poll();
        if (!chickenFeather.valid()) {
            return;
        }

        int oldChickenFeatherCount = ctx.inventory.select().id(chickenFeatherId).count(true);

        // Move to the chicken feather if necessary
        if (!chickenFeather.inViewport()) {
            System.out.println("Moving to chicken feather.");
            ctx.movement.step(chickenFeather);
            ctx.camera.turnTo(chickenFeather);
        }

        WaitBeforeAndAfterTakeChickenFeather(chickenFeather);

        // Workaround to wait until taking chicken feather has completed
        if (TakeChickenFeatherSuccessful(oldChickenFeatherCount)) {
            System.out.println("Successful.");
        } else {
            System.out.println("Picking up chicken feather was unsuccessful.");
        }

        System.out.println("...");
    }

    /**
     * Wait until player is idle then take the chicken feather on the ground.
     * @param chickenFeather chicken feather to pick up
     */
    private void WaitBeforeAndAfterTakeChickenFeather(GroundItem chickenFeather) {
        System.out.println("Found a feather, picking it up...");

        WaitUntilPlayerIdleUtil.Wait(ctx);
        chickenFeather.interact("Take");
        WaitUntilPlayerIdleUtil.Wait(ctx, 1000, 2);
    }

    /**
     * Check if the chicken feather count in the player's inventory has increased.
     * @param oldChickenFeatherCount previous chicken feathers in inventory
     * @return true if feather count increased
     */
    private boolean TakeChickenFeatherSuccessful(int oldChickenFeatherCount) {
        return ctx.inventory.select().id(chickenFeatherId).count(true) > oldChickenFeatherCount;
    }
}
