package scripts.task;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

/**
 * Task class for defining an action that will be conditionally (activate) executed.
 * @param <C> Client context
 */
public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {
    public Task(C ctx) {
        super(ctx);
    }

    /**
     * Whether to execute the action.
     * @return true to execute the action else false.
     */
    public abstract boolean activate();

    /**
     * Execute the action.
     */
    public abstract void execute();
}
