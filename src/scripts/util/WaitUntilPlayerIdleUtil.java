package scripts.util;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

/**
 * Utility class to wait until the player is idle.
 */
public class WaitUntilPlayerIdleUtil {
    private static int playerIdle = -1;

    /**
     * Waits until the player is idle.
     * Fixed wait = 500ms * 4 retries = 2s
     */
    public static void Wait(ClientContext ctx) {
        Condition.wait(() -> ctx.players.local().animation() == playerIdle, 500, 4);
    }
}
