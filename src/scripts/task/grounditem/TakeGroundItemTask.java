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
    private static final int CHICKEN_FEATHER_ID = 314;
    private static final int PLAYER_IDLE = -1;

    public TakeGroundItemTask(ClientContext ctx) {
        super(ctx);
    }

    /**
     * Condition to pick up a chicken feather.
     * @return true to pick up the chicken feather
     */
    @Override
    public boolean activate() {
        boolean chickenFeathersExistInInventory = ctx.inventory.select().id(CHICKEN_FEATHER_ID).count(true) > 0;

        // Full inventory with feather is okay
        if (ctx.inventory.isFull() && !chickenFeathersExistInInventory) {
            System.out.println("Inventory full: dropping unwanted chicken loot.");
            DropItemsUtil.DropAllUnwantedChickenLoot(ctx);
        }

        // Inventory is not full, chicken feather exists and player is idle
        return (!ctx.inventory.isFull() || chickenFeathersExistInInventory)
            && !ctx.groundItems.select().id(CHICKEN_FEATHER_ID).isEmpty()
            && ctx.players.local().animation() == PLAYER_IDLE;
    }

    /**
     * Attempts to pick up a chicken feather in the area and returns the number picked up.
     * @param area the area to execute the action
     * @return the number of chicken feathers picked up from the action
     */
    @Override
    public int execute(Area area) {
        int chickedFeathersPickedUpCount = 0;
        GroundItem chickenFeather = ctx.groundItems.within(area).nearest().poll();
        if (!chickenFeather.valid()) {
            return 0;
        }

        int oldChickenFeatherCount = ctx.inventory.select().id(CHICKEN_FEATHER_ID).count(true);

        // Move to the chicken feather if necessary
        if (!chickenFeather.inViewport()) {
            System.out.println("Moving to chicken feather.");
            ctx.movement.step(chickenFeather);
            ctx.camera.turnTo(chickenFeather);
        }

        WaitBeforeAndAfterTakeChickenFeather(chickenFeather);
        chickedFeathersPickedUpCount = ChickenFeathersPickedUpCount(oldChickenFeatherCount);

        // Workaround to wait until taking chicken feather has completed
        if (chickedFeathersPickedUpCount > 0) {
            System.out.println("Successful.");
        } else {
            System.out.println("Picking up chicken feather was unsuccessful.");
        }

        System.out.println("...");
        return chickedFeathersPickedUpCount;
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
     * Number of chicken feathers picked up.
     * @param oldChickenFeatherCount previous chicken feathers in inventory
     * @return number of chicken feathers picked up
     */
    private int ChickenFeathersPickedUpCount(int oldChickenFeatherCount) {
        int newChickenFeatherCount = ctx.inventory.select().id(CHICKEN_FEATHER_ID).count(true);
        return newChickenFeatherCount > oldChickenFeatherCount ? newChickenFeatherCount - oldChickenFeatherCount: 0;
    }
}
