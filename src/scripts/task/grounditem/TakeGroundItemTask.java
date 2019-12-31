package scripts.task.grounditem;

import scripts.task.Task;
import scripts.util.WaitUntilPlayerIdleUtil;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
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
        // Inventory is not full, chicken feather exists and player is idle
        return !ctx.inventory.isFull()
            && !ctx.groundItems.select().id(chickenFeatherId).isEmpty()
            && ctx.players.local().animation() == playerIdle;
    }

    @Override
    public void execute() {
        GroundItem chickenFeather = ctx.groundItems.nearest().poll();
        int oldChickenFeatherCount = ctx.inventory.select().id(chickenFeatherId).count();

        // Move to the feather if necessary
        if (!chickenFeather.inViewport()) {
            System.out.println("Moving to feather.");
            ctx.movement.step(chickenFeather);
            ctx.camera.turnTo(chickenFeather);
        }

        // Much better to check inventory count
        if (WaitBeforeTakeChickenFeather(chickenFeather) || TakeChickenFeatherSuccessful(oldChickenFeatherCount)) {
            System.out.println("Successful.");
        } else {
            System.out.println("Picking up feather was unsuccessful.");
        }

        System.out.println("...");
        Condition.sleep(Random.nextInt(1000, 3000));
    }

    /**
     * Wait until player is idle then take the chicken feather on the ground.
     * @param chickenFeather chicken feather to pick up
     * @return if the feather was successfully picked up
     */
    private boolean WaitBeforeTakeChickenFeather(GroundItem chickenFeather) {
        System.out.println("Found a feather, picking it up...");
        WaitUntilPlayerIdleUtil.Wait(ctx);

        return chickenFeather.interact("Take");
    }

    /**
     * Check if the chicken feather count in the player's inventory has increased.
     * @param oldChickenFeatherCount previous chicken feathers in inventory
     * @return true if feature count increased
     */
    private boolean TakeChickenFeatherSuccessful(int oldChickenFeatherCount) {
        return ctx.inventory.select().id(chickenFeatherId).count() > oldChickenFeatherCount;
    }
}
