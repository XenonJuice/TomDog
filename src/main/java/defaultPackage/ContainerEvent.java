package defaultPackage;

import java.util.EventObject;

/**
 * 表示在 Container 上发生的重要变化时，用于通知监听器的事件对象。
 * 该类继承自 EventObject，包含了与事件相关的详细信息，例如事件的类型、发生事件的容器以及任何相关数据。
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/07/22 20:13:30 $
 */
public class ContainerEvent extends EventObject {

    // 事件类型，用于标识具体的事件类型（例如添加子容器、移除子容器等）
    private final String type;

    // 与事件相关的数据，可以是任意对象类型
    private final Object data;

    /**
     * 创建一个新的 ContainerEvent 对象。
     *
     * @param container 发生事件的容器
     * @param type 事件的类型
     * @param data 与事件相关的附加数据
     */
    public ContainerEvent(Container container, String type, Object data) {
        super(container);
        this.type = type;
        this.data = data;
    }

    /**
     * 返回与该事件关联的数据。
     *
     * @return 与该事件关联的数据
     */
    public Object getData() {
        return this.data;
    }

    /**
     * 返回发生该事件的容器。
     *
     * @return 发生该事件的容器
     */
    public Container getContainer() {
        return (Container) getSource();
    }

    /**
     * 返回事件的类型。
     *
     * @return 事件的类型
     */
    public String getType() {
        return this.type;
    }

    /**
     * 返回该事件的字符串表示形式，包含容器、类型和数据的相关信息。
     *
     * @return 该事件的字符串表示形式
     */
    @Override
    public String toString() {
        return "ContainerEvent['" + getContainer() + "','" + type + "','" + data + "']";
    }
}
