package defaultPackage;



/**
 * 参考tomcat源码
 * 定义父容器可能实现的方法的接口，用于选择一个子容器来处理特定请求，
 * 并且可以选择性地修改请求的属性以反映所做的选择。
 * <p>
 * 一个典型的容器可能与一个处理所有请求的单个 Mapper 相关联，
 * 或者每种请求协议有一个 Mapper，从而允许同一个容器同时支持多个协议。
 */
public interface Mapper {

    /**
     * 返回与此 Mapper 关联的容器。
     */
    public Container getContainer();

    /**
     * 设置与此 Mapper 关联的容器。
     *
     * @param container 新关联的容器
     * @throws IllegalArgumentException 如果此容器不适合此 Mapper
     */
    public void setContainer(Container container);

    /**
     * 返回此 Mapper 所负责的协议。
     */
    public String getProtocol();

    /**
     * 设置此 Mapper 所负责的协议。
     *
     * @param protocol 新关联的协议
     */
    public void setProtocol(String protocol);

    /**
     * 基于请求的特征 返回应该用于处理此请求的子容器。
     * 如果无法识别出这样的子容器，则返回 <code>null</code>。
     *
     * @param request 正在处理的请求
     * @param update  是否更新请求以反映映射的选择？
     */
    public Container map(Request request, boolean update);

}
