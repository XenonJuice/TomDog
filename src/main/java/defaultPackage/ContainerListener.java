package defaultPackage;


import java.util.EventObject;


/**
 * Interface defining a listener for significant Container generated events.
 * Note that "container start" and "container stop" events are normally
 * LifecycleEvents, not ContainerEvents.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/07/22 20:13:30 $
 */

public interface ContainerListener {


    /**
     * 确认指定事件的发生.
     *
     * @param event ContainerEvent that has occurred
     */
    public void containerEvent(ContainerEvent event);


}
