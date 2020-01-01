package scripts.util;

import java.awt.*;

/**
 * Class for common utilities.
 */
public class CommonUtil {
    public static final int PLAYER_IDLE = -1;

    /**
     * OSRS client's corner points, this will be inaccurate with client resizing.
     */
    public static class ClientCornerPoint {
        public static final Point NE = new Point(770, 0);
        public static final Point SE = new Point(770, 500);
        public static final Point SW = new Point(0, 500);
        public static final Point NW = new Point(0, 0);
    }
}
