package scripts.util;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

/**
 * Utility class to wait until the player is idle.
 */
public class WaitUntilPlayerIdleUtil {
    private static final int PLAYER_IDLE = -1;

    /**
     * Waits until the player is idle.
     * Fixed wait = 500ms * 4 retries = 2s
     * @param ctx client context
     */
    public static void Wait(ClientContext ctx) {
        Condition.wait(() -> ctx.players.local().animation() == PLAYER_IDLE, 500, 4);
    }

    /**
     * Waits until the player is idle using the specified intervals.
     * @param ctx client context
     * @param waitInMs interval to wait in ms
     * @param retries number of retries for waiting until idle
     */
    public static void Wait(ClientContext ctx, int waitInMs, int retries) {
        Condition.wait(() -> ctx.players.local().animation() == PLAYER_IDLE, waitInMs, retries);
    }
}
