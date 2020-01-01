package scripts;

import scripts.task.Task;
import scripts.task.grounditem.TakeGroundItemTask;
import scripts.ui.ChickenFeatherSummaryUi;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(
    name = "Chicken Feather Picker",
    properties = "author=Machiavellianism; topic=1353781; client=4;",
    description = "Picks up chicken feathers at the Lumbridge farm"
)
public class ChickenFeatherPicker extends PollingScript<ClientContext> implements PaintListener {
    private int totalChickenFeathersPickedUp;
    private List<Task> taskList = new ArrayList<Task>();

    private static final long START_TIME = System.currentTimeMillis();

    // Lumbridge chicken area, this does not encompass the gates or farm house
    private static final Area LUMBRIDGE_CHICKEN_AREA = new Area(
        new Tile(3235, 3301, 0), // NE
        new Tile(3235, 3295, 0), // SE
        new Tile(3225, 3295, 0), // SW
        new Tile(3225, 3301, 0)  // NW
    );

    @Override
    public void start() {
        log.info("Welcome to the basic chicken feather picker for Lumbridge farm!");

        totalChickenFeathersPickedUp = 0;
        taskList.addAll(Arrays.asList(new TakeGroundItemTask(ctx)));
    }

    @Override
    public void poll() {
        // Actions only valid within Lumbridge for now
        for (Task task : taskList) {
            if (task.activate()) {
                totalChickenFeathersPickedUp += task.execute(LUMBRIDGE_CHICKEN_AREA);
            }
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        ChickenFeatherSummaryUi.ShowStatisticsSummary(graphics, START_TIME, totalChickenFeathersPickedUp);
    }
}
