import javax.servlet.http.Cookie;

/**
 * copy from apache tomcat
 * -----------------------------
 * <b>HttpResponse</b> 是对 <code>HttpServletResponse</code> 的封装，
 * 用于处理请求并生成相应的响应。
 */
public interface HttpResponse extends Response {

    /**
     * 返回为此响应设置的所有 Cookie 的数组，
     * 如果未设置任何 Cookie，则返回长度为零的数组。
     *
     * @return 所有设置的 Cookie 数组
     */
    Cookie[] getCookies();

    /**
     * 返回指定 Header 的值，如果未设置此 Header，则返回 <code>null</code>。
     * 如果为此名称添加了多个值，仅返回第一个值；使用 getHeaderValues() 获取所有值。
     *
     * @param name 要查找的 Header 名称
     * @return 指定 Header 的值
     */
    String getHeader(String name);

    /**
     * 返回为此响应设置的所有 Header 名称的数组，
     * 如果未设置任何 Header，则返回长度为零的数组。
     *
     * @return 所有设置的 Header 名称数组
     */
    String[] getHeaderNames();

    /**
     * 返回与指定 Header 名称关联的所有 Header 值的数组，
     * 如果没有此类 Header 值，则返回长度为零的数组。
     *
     * @param name 要查找的 Header 名称
     * @return 指定 Header 的所有值数组
     */
    String[] getHeaderValues(String name);

    /**
     * 返回使用 <code>sendError()</code> 设置的错误消息。
     *
     * @return 错误消息
     */
    String getMessage();

    /**
     * 返回与此响应关联的 HTTP 状态代码。
     *
     * @return HTTP 状态代码
     */
    int getStatus();

    /**
     * 重置此响应，并指定 HTTP 状态代码和相应的消息。
     *
     * @param status HTTP 状态代码
     * @param message 对应的消息
     * @exception IllegalStateException 如果此响应已提交
     */
    void reset(int status, String message);
}
