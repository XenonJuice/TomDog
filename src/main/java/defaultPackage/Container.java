package defaultPackage;

import defaultPackage.Mapper;
import defaultPackage.Request;
import defaultPackage.Response;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.naming.directory.DirContext;

/**
 * 一个 <b>Container</b> 是一个可以执行来自客户端的请求并基于这些请求返回响应的对象。
 * 容器可以选择性地支持一组按运行时配置顺序处理请求的 Valves，
 * 通过实现 <b>Pipeline</b> 接口来实现这一点。
 * <p>
 * 在 Catalina 中，容器会存在于多个概念层级。以下是常见的例子：
 * <ul>
 * <li><b>Engine</b> - 代表整个 Catalina servlet 引擎，
 *     通常包含一个或多个子容器，这些子容器可能是 Host 或 Context 的实现，或其他自定义组。
 * <li><b>Host</b> - 代表一个虚拟主机，包含多个 Context。
 * <li><b>Context</b> - 代表单个 ServletContext，通常包含一个或多个支持的 servlet 的 Wrapper。
 * <li><b>Wrapper</b> - 代表一个独立的 servlet 定义（如果 servlet 自身实现了 SingleThreadModel，则可能支持多个 servlet 实例）。
 * </ul>
 * 一个 Catalina 部署不必包含所有上述层级的容器。例如，嵌入在网络设备（如路由器）中的管理应用可能只包含一个 Context 和几个 Wrapper，甚至只有一个 Wrapper。如果应用程序较小，因此容器的实现需要设计成即使在缺少父容器的情况下也能正确运行。
 * <p>
 * 容器还可以与多个支持组件相关联，这些组件可以通过附加到父容器来共享，或者单独定制。目前支持的组件包括：
 * <ul>
 * <li><b>Loader</b> - 类加载器，用于将新 Java 类集成到运行 Catalina 的 JVM 中。
 * <li><b>Logger</b> - <code>ServletContext</code> 接口中 <code>log()</code> 方法的实现。
 * <li><b>Manager</b> - 管理与此容器关联的会话池。
 * <li><b>Realm</b> - 只读接口，用于安全域的身份验证和角色授权。
 * <li><b>Resources</b> - JNDI 目录上下文，允许访问静态资源，使 Catalina 嵌入到更大的服务器中时能够自定义与现有服务器组件的链接。
 * </ul>
 *
 * @author Craig R. McClanahan
 * @author Remy Maucherat
 * @version $Revision: 1.7 $ $Date: 2001/11/09 19:37:50 $
 */
public interface Container {

    // ----------------------------------------------------- 常量定义

    /**
     * 当通过 <code>addChild()</code> 添加子容器时发送的 ContainerEvent 事件类型。
     */
    public static final String ADD_CHILD_EVENT = "addChild";

    /**
     * 当通过 <code>addMapper()</code> 添加 Mapper 时发送的 ContainerEvent 事件类型。
     */
    public static final String ADD_MAPPER_EVENT = "addMapper";

    /**
     * 如果此容器支持管道，通过 <code>addValve()</code> 添加阀门时发送的 ContainerEvent 事件类型。
     */
    public static final String ADD_VALVE_EVENT = "addValve";

    /**
     * 当通过 <code>removeChild()</code> 移除子容器时发送的 ContainerEvent 事件类型。
     */
    public static final String REMOVE_CHILD_EVENT = "removeChild";

    /**
     * 当通过 <code>removeMapper()</code> 移除 Mapper 时发送的 ContainerEvent 事件类型。
     */
    public static final String REMOVE_MAPPER_EVENT = "removeMapper";

    /**
     * 如果此容器支持管道，通过 <code>removeValve()</code> 移除阀门时发送的 ContainerEvent 事件类型。
     */
    public static final String REMOVE_VALVE_EVENT = "removeValve";

    // ------------------------------------------------------------- 属性方法

    /**
     * 返回此容器实现的描述信息和对应的版本号，格式为 <code>&lt;description&gt;/&lt;version&gt;</code>。
     */
    public String getInfo();

    /**
     * 返回与此容器相关联的加载器。如果没有相关联的加载器，
     * 则返回与我们父容器（如果有的话）相关联的加载器；否则，返回 <code>null</code>。
     */
    public Loader getLoader();

    /**
     * 设置与此容器关联的加载器。
     *
     * @param loader 新关联的加载器
     */
    public void setLoader(Loader loader);

    /**
     * 返回与此容器关联的 Logger。如果没有相关联的 Logger，
     * 则返回与我们父容器（如果有的话）相关联的 Logger；否则返回 <code>null</code>。
     */
    public Logger getLogger();

    /**
     * 设置与此容器关联的 Logger。
     *
     * @param logger 新关联的 Logger
     */
    public void setLogger(Logger logger);

    /**
     * 返回与此容器关联的 Manager。如果没有相关联的 Manager，
     * 则返回与我们父容器（如果有的话）相关联的 Manager；否则返回 <code>null</code>。
     */
    public Manager getManager();

    /**
     * 设置与此容器关联的 Manager。
     *
     * @param manager 新关联的 Manager
     */
    public void setManager(Manager manager);

    /**
     * 返回与此容器关联的 Cluster。如果没有相关联的 Cluster，
     * 则返回与我们父容器（如果有的话）相关联的 Cluster；否则返回 <code>null</code>。
     */
    public Cluster getCluster();

    /**
     * 设置与此容器关联的 Cluster。
     *
     * @param cluster 新关联的 Cluster
     */
    public void setCluster(Cluster cluster);

    /**
     * 返回描述此容器的名称字符串（便于理解）。在特定父容器的子容器集中，容器名称必须唯一。
     */
    public String getName();

    /**
     * 设置描述此容器的名称字符串（便于理解）。在特定父容器的子容器集中，容器名称必须唯一。
     *
     * @param name 此容器的新名称
     * @throws IllegalStateException 如果此容器已经被添加到父容器的子容器集中（之后名称不可更改）
     */
    public void setName(String name);

    /**
     * 返回此容器所属的父容器（如果有的话）。如果没有定义父容器，则返回 <code>null</code>。
     */
    public Container getParent();

    /**
     * 设置将此容器作为子容器添加到的父容器。此容器可能通过抛出异常拒绝附加到指定的容器。
     *
     * @param container 要将此容器添加为子容器的父容器
     * @throws IllegalArgumentException 如果此容器拒绝附加到指定的父容器
     */
    public void setParent(Container container);

    /**
     * 返回 Web 应用的父类加载器（如果有的话）。
     */
    public ClassLoader getParentClassLoader();

    /**
     * 设置 Web 应用的父类加载器（如果有的话）。
     * 此调用仅在配置加载器之前有意义，指定的值（如果非空）应作为类加载器构造函数的参数传递。
     *
     * @param parent 新的父类加载器
     */
    public void setParentClassLoader(ClassLoader parent);

    /**
     * 返回与此容器关联的 Realm。如果没有相关联的 Realm，
     * 则返回与我们父容器（如果有的话）相关联的 Realm；否则返回 <code>null</code>。
     */
    public Realm getRealm();

    /**
     * 设置与此容器关联的 Realm。
     *
     * @param realm 新关联的 Realm
     */
    public void setRealm(Realm realm);

    /**
     * 返回与此容器关联的 Resources。如果没有相关联的 Resources 对象，
     * 则返回与我们父容器（如果有的话）相关联的 Resources；否则返回 <code>null</code>。
     */
    public DirContext getResources();

    /**
     * 设置与此容器关联的 Resources 对象。
     *
     * @param resources 新关联的 Resources
     */
    public void setResources(DirContext resources);

    // --------------------------------------------------------- 公共方法

    /**
     * 向与此容器关联的子容器集中添加一个新的子容器（如果支持）。
     * 在将此容器添加到子容器集中之前，必须调用子容器的 <code>setParent()</code> 方法，以此容器作为参数。
     *
     * @param child 要添加的新子容器
     * @throws IllegalArgumentException 如果子容器的 <code>setParent()</code> 方法抛出此异常
     * @throws IllegalArgumentException 如果新子容器的名称与此容器的现有子容器名称不唯一
     * @throws IllegalStateException    如果此容器不支持子容器
     */
    public void addChild(Container child);

    /**
     * 向此组件添加容器事件监听器。
     *
     * @param listener 要添加的监听器
     */
    public void addContainerListener(ContainerListener listener);

    /**
     * 添加与此容器关联的指定 Mapper。
     *
     * @param mapper 对应的 Mapper 实现
     * @throws IllegalArgumentException 如果 Mapper 的 <code>setContainer()</code> 方法抛出此异常
     */
    public void addMapper(Mapper mapper);

    /**
     * 向此组件添加属性更改监听器。
     *
     * @param listener 要添加的监听器
     */
    public void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * 返回与此容器关联的具有指定名称的子容器（如果有）；否则，返回 <code>null</code>。
     *
     * @param name 要检索的子容器的名称
     */
    public Container findChild(String name);

    /**
     * 返回与此容器关联的子容器集。如果此容器没有子容器，则返回一个长度为零的数组。
     */
    public Container[] findChildren();

    /**
     * 返回与此容器关联的容器监听器集。如果此容器没有注册的容器监听器，则返回一个长度为零的数组。
     */
    public ContainerListener[] findContainerListeners();

    /**
     * 返回与指定协议关联的 Mapper（如果有）。如果只定义了一个 Mapper，则对所有协议使用它。
     * 如果没有匹配的 Mapper，则返回 <code>null</code>。
     *
     * @param protocol 要查找 Mapper 的协议
     */
    public Mapper findMapper(String protocol);

    /**
     * 返回与此容器关联的 Mappers 集。如果此容器没有 Mappers，则返回一个长度为零的数组。
     */
    public Mapper[] findMappers();

    /**
     * 处理指定的请求，并根据此特定容器的设计生成相应的响应。
     *
     * @param request  要处理的请求
     * @param response 要生成的响应
     * @throws IOException      如果在处理过程中发生输入/输出错误
     * @throws ServletException 如果在处理此请求时抛出 ServletException
     */
    public void invoke(Request request, Response response)
            throws IOException, ServletException;

    /**
     * 返回应基于请求特征用于处理此请求的子容器。如果无法识别出这样的子容器，则返回 <code>null</code>。
     *
     * @param request 正在处理的请求
     * @param update  是否更新请求以反映映射的选择？
     */
    public Container map(Request request, boolean update);

    /**
     * 从此父容器关联的子容器集中移除一个现有的子容器。
     *
     * @param child 要移除的现有子容器
     */
    public void removeChild(Container child);

    /**
     * 从此组件中移除容器事件监听器。
     *
     * @param listener 要移除的监听器
     */
    public void removeContainerListener(ContainerListener listener);

    /**
     * 移除与此容器关联的 Mapper（如果有）。
     *
     * @param mapper 要移除的 Mapper
     */
    public void removeMapper(Mapper mapper);

    /**
     * 从此组件中移除属性更改监听器。
     *
     * @param listener 要移除的监听器
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);

}
