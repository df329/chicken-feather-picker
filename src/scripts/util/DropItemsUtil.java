package scripts.util;

import org.powerbot.script.rt4.ClientContext;

/**
 * Utility class for dropping items.
 */
public class DropItemsUtil {
    private static final int[] UNWANTED_CHICKEN_LOOT =
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
    public static void DropAllUnwantedChickenLoot(ClientContext ctx) {
        ctx.inventory.drop(ctx.inventory.select().id(UNWANTED_CHICKEN_LOOT));
    }
}
