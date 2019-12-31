package scripts;

import scripts.task.Task;
import scripts.task.grounditem.TakeGroundItemTask;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(
    name = "Chicken Feather Picker",
    properties = "author=Machiavellianism; topic=1353781; client=4;",
    description = "Picks up chicken feathers at the Lumbridge farm"
)
public class ChickenFeatherPicker extends PollingScript<ClientContext> {
    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        log.info("Welcome to the basic chicken feather picker for Lumbridge farm!");

        taskList.addAll(Arrays.asList(new TakeGroundItemTask(ctx)));
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }
    }
}
