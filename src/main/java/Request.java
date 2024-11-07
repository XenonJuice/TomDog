import java.io.InputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

/**
 * copy from apache tomcat
 * -----------------------------
 * <b>Request</b> 是对 <code>ServletRequest</code> 的封装，
 * 用于处理请求并生成相应的 <code>Response</code>。
 */
public interface Request {
    /**
     * 获取此请求的授权凭据。
     */
    String getAuthorization();

    /**
     * 设置此请求的授权凭据。
     */
    void setAuthorization(String authorization);

    /**
     * 获取接收该请求的连接器。
     */
    Connector getConnector();

    /**
     * 设置接收该请求的连接器。
     */
    void setConnector(Connector connector);

    /**
     * 获取此请求正在处理的上下文。
     */
    Context getContext();

    /**
     * 设置此请求正在处理的上下文。
     */
    void setContext(Context context);

    /**
     * 返回此请求实现的信息描述。
     */
    String getInfo();

    /**
     * 获取封装的 <code>ServletRequest</code>。
     */
    ServletRequest getRequest();

    /**
     * 获取与此请求关联的响应。
     */
    Response getResponse();

    /**
     * 设置与此请求关联的响应。
     */
    void setResponse(Response response);

    /**
     * 获取接收此请求的 Socket（如果有）。
     */
    Socket getSocket();

    /**
     * 设置接收此请求的 Socket。
     */
    void setSocket(Socket socket);

    /**
     * 获取与此请求关联的输入流。
     */
    InputStream getStream();

    /**
     * 设置与此请求关联的输入流。
     */
    void setStream(InputStream stream);

    /**
     * 获取此请求正在处理的包装器。
     */
    Wrapper getWrapper();

    /**
     * 设置此请求正在处理的包装器。
     */
    void setWrapper(Wrapper wrapper);

    /**
     * 创建并返回用于读取此请求内容的 ServletInputStream。
     */
    ServletInputStream createInputStream() throws IOException;

    /**
     * 执行所有必要操作以刷新和关闭输入流或读取器。
     */
    void finishRequest() throws IOException;

    /**
     * 获取指定名称的内部备注对象，或返回 <code>null</code>。
     */
    Object getNote(String name);

    /**
     * 返回包含所有备注绑定名称的迭代器。
     */
    Iterator getNoteNames();

    /**
     * 释放所有对象引用并初始化实例变量，为重用该对象做准备。
     */
    void recycle();

    /**
     * 移除指定名称的内部备注对象。
     */
    void removeNote(String name);

    /**
     * 设置此请求的内容长度。
     */
    void setContentLength(int length);

    /**
     * 设置此请求的内容类型（可选地包括字符编码）。
     */
    void setContentType(String type);

    /**
     * 将对象绑定到指定名称的内部备注中。
     */
    void setNote(String name, Object value);

    /**
     * 设置与此请求关联的协议名称和版本。
     */
    void setProtocol(String protocol);

    /**
     * 设置与此请求关联的远程 IP 地址。
     */
    void setRemoteAddr(String remote);

    /**
     * 设置与此请求关联的方案名称。
     */
    void setScheme(String scheme);

    /**
     * 设置 <code>isSecure()</code> 返回的值。
     */
    void setSecure(boolean secure);

    /**
     * 设置处理此请求的服务器名称。
     */
    void setServerName(String name);

    /**
     * 设置处理此请求的服务器端口号。
     */
    void setServerPort(int port);
}
