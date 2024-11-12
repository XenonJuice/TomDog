
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

/**
 * 参考tomcat源码
 * <b>Response</b> 是对 <code>ServletResponse</code> 的封装，
 * 根据对应的 <code>Request</code> 生成响应。
 */
public interface Response {
  /**
     * 获取返回响应的连接器。
     */
  Connector getConnector();

    /**
     * 设置返回响应的连接器。
     */
    void setConnector(Connector connector);

    /**
     * 获取实际写入输出流的字节数。
     */
    int getContentCount();

    /**
     * 获取与该响应关联的上下文。
     */
    Context getContext();

    /**
     * 设置与该响应关联的上下文。
     */
    void setContext(Context context);

    /**
     * 设置应用程序提交标志。
     */
    void setAppCommitted(boolean appCommitted);

    /**
     * 获取应用程序提交标志。
     */
    boolean isAppCommitted();

    /**
     * 获取 "处理中包含" 标志。
     */
    boolean getIncluded();

    /**
     * 设置 "处理中包含" 标志。
     */
    void setIncluded(boolean included);

    /**
     * 返回该响应实现的信息描述。
     */
    String getInfo();

    /**
     * 获取与该响应关联的请求。
     */
    Request getRequest();

    /**
     * 设置与该响应关联的请求。
     */
    void setRequest(Request request);

    /**
     * 获取该对象所包装的 <code>ServletResponse</code>。
     */
    ServletResponse getResponse();

    /**
     * 获取与该响应关联的输出流。
     */
    OutputStream getStream();

    /**
     * 设置与该响应关联的输出流。
     */
    void setStream(OutputStream stream);

    /**
     * 设置挂起标志。
     */
    void setSuspended(boolean suspended);

    /**
     * 获取挂起标志。
     */
    boolean isSuspended();

    /**
     * 设置错误标志。
     */
    void setError();

    /**
     * 获取错误标志。
     */
    boolean isError();

    // --------------------------------------------------------- 公共方法

    /**
     * 创建并返回用于写入响应内容的 ServletOutputStream。
     */
    ServletOutputStream createOutputStream() throws IOException;

    /**
     * 执行所有操作以刷新并关闭输出流或写入器。
     */
    void finishResponse() throws IOException;

    /**
     * 获取该响应的内容长度。
     */
    int getContentLength();

    /**
     * 获取该响应的内容类型。
     */
    String getContentType();

    /**
     * 返回可用于呈现错误消息的 PrintWriter。
     */
    PrintWriter getReporter();

    /**
     * 释放所有对象引用并初始化实例变量，为重用该对象做准备。
     */
    void recycle();

    /**
     * 重置数据缓冲区，但不包括状态或头信息。
     */
    void resetBuffer();

    /**
     * 发送请求的确认响应。
     */
    void sendAcknowledgement() throws IOException;
}
