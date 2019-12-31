package scripts.util;

import org.powerbot.script.rt4.ClientContext;

public class DropUnwantedItemsUtil {
    private static int[] unwantedChickenLoot =
    {
        526,  // bones
        2138, // raw chicken
        1944  // egg
    };

    /**
     * Drops all unwanted items from chickens, these are:
     * - bones
     * - egg
     * - raw chicken
     * @param ctx client context
     */
    public static void DropAll(ClientContext ctx) {
        ctx.inventory.drop(ctx.inventory.select().id(unwantedChickenLoot));
    }
}
