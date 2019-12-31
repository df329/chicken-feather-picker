package scripts.task.grounditem;

import scripts.task.Task;
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
        boolean pickedUp = false;

        Condition.sleep(Random.nextInt(350, 500));

        GroundItem whiteFeather = ctx.groundItems.nearest().poll();
        if (whiteFeather.inViewport()) {
            System.out.println("Found a feather, picking it up...");
            pickedUp = whiteFeather.interact("Take");

            // If moving to a feather, wait until the player has arrived
            Condition.sleep(Random.nextInt(100, 2000));
        } else {
            System.out.println("Moving to feather.");
            ctx.movement.step(whiteFeather);
            ctx.camera.turnTo(whiteFeather);

            // If moving to a feather, wait until the player has arrived
            Condition.sleep(Random.nextInt(2000, 4000));
        }

        if (!pickedUp) {
            System.out.println("Picking up feather was unsuccessful.");
        } else {
            System.out.println("Successful.");
        }

        System.out.println("...");

        Condition.sleep(Random.nextInt(500, 4000));
    }
}
