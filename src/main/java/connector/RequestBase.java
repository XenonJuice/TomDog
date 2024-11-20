package connector;

import defaultPackage.Request;
import defaultPackage.Response;
import util.Enumerator;
import util.RequestUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;


/**
 *<b>Request</b> 接口的便利基础实现，
 * 可用于大多数连接器所需的请求实现。
 * 只需实现特定于连接器的方法。
 */

public abstract class RequestBase
        implements ServletRequest, Request {


    /**
     * 与 defaultPackage.Request 相关的属性，
     * 以属性名称为键。
     */
    protected final HashMap<String, Object> attributes = new HashMap<>();


    /**
     * 与 defaultPackage.Request 一起发送的授权凭据。
     */
    protected String authorization = null;


    /**
     *  defaultPackage.Request 的字符编码
     */
    protected String characterEncoding = null;

//TODO 组件未实现
    /*
      接收defaultPackage.Request 的连接器
     */
    // protected Connector connector = null;


    /**
     * 与此请求相关的内容长度
     */
    protected int contentLength = -1;


    /**
     * 与此请求相关的内容类型。
     */
    protected String contentType = null;

//TODO 组件未实现
    /*
      处理此 defaultPackage.Request 的上下文
     */
    // protected Context context = null;


    /**
     * 如果未指定，则使用默认语言环境。
     */
    protected static Locale defaultLocale = Locale.getDefault();


    /**
     * 与此请求相关的外观类
     */
    //TODO
    // protected RequestFacade facade = new RequestFacade();


    /**
     * 与此 defaultPackage.Request 关联的输入流。
     */
    protected InputStream input = null;


    /**
     * 与此defaultPackage.Request关联的首选区域设置。
     */
    protected final ArrayList locales = new ArrayList();


    /**
     * 与此请求相关的内部注释。
     */
    private transient HashMap notes = new HashMap();


    /**
     * 与此 defaultPackage.Request 关联的协议名称和版本。
     */
    protected String protocol = null;


    /**
     * 如果有的话，由 <code>getReader</code> 返回的reader。
     */
    protected BufferedReader reader = null;


    /**
     * 与此请求相关的远程地址。
     */
    protected String remoteAddr = null;


    /**
     * 远程主机的完全限定名。
     */
    protected String remoteHost = null;

    /**
     * 与此请求相关的响应。
     */
    protected Response response = null;


    /**
     * 与此 defaultPackage.Request 相关的scheme。
     */
    protected String scheme = null;


    /**
     * 检查是否为安全链接
     */
    protected boolean secure = false;


    /**
     * 与此defaultPackage.Request关联的服务器名称。
     */
    protected String serverName = null;


    /**
     * 与此 defaultPackage.Request 关联的服务器端口。
     */
    protected int serverPort = -1;


    /**
     * 当前请求关联的套接字。
     */
    protected Socket socket = null;


    /**
     * 由 <code>getInputStream()</code> 返回的 ServletInputStream（如果有）
     */
    protected ServletInputStream stream = null;


//TODO 组件未实现
    /*
      处理此 defaultPackage.Request 的包装器。
     */
    //protected Wrapper wrapper = null;


    /**
     *
     */
    public String getAuthorization() {

        return (this.authorization);

    }


    /**
     * 设置随此请求发送的授权凭据。
     *
     * @param authorization 授权凭据
     */
    public void setAuthorization(String authorization) {

        this.authorization = authorization;

    }

//TODO 组件未实现
    /**
     * 返回接收此 defaultPackage.Request 的连接器.
     */
//    public Connector getConnector() {
//
//        return (this.connector);
//
//    }


    /**
     * 设置接收此 defaultPackage.Request 的连接器。
     *
     * @param connector 连接器
     */
//    public void setConnector(Connector connector) {
//
//        this.connector = connector;
//
//    }


    /**
     * 返回正在处理此 defaultPackage.Request 的上下文。
     */
//    public Context getContext() {
//
//        return (this.context);
//
//    }


    /**
     * 设置处理此 defaultPackage.Request 的上下文。此操作必须在识别到合适的上下文后立即调用，
     * 因为它确定了 <code>getContextPath()</code> 返回的值，
     * 从而启用请求 URI 的解析。
     *
     * @param context The newly associated Context
     */
//    public void setContext(Context context) {
//
//        this.context = context;
//
//    }


//TODO
    /**
     * 返回此对象作为外观的 <code>ServletRequest</code>。该方法必须由子类实现。
     */
//    public ServletRequest getRequest() {
//
//        return (facade);
//
//    }


    /**
     * 返回与此 defaultPackage.Request 关联的 defaultPackage.Response。
     */
    public Response getResponse() {

        return (this.response);

    }


    /**
     * 设置与此 defaultPackage.Request 关联的 defaultPackage.Response。
     *
     * @param response 关联响应
     */
    public void setResponse(Response response) {

        this.response = response;

    }


    /**
     * 返回收到此 defaultPackage.Request 的 Socket（如果有）。
     * 这应该<strong>仅</strong>用于访问关于此 Socket 的底层状态
     * 信息，例如与 SSLSocket 相关联的 SSLSession。
     */
    public Socket getSocket() {

        return (this.socket);

    }


    /**
     * 设置接收此 defaultPackage.Request 的套接字（如果有）。
     *
     * @param socket 接收此请求的套接字
     */
    public void setSocket(Socket socket) {

        this.socket = socket;

    }


    /**
     * 返回与此 defaultPackage.Request 相关联的输入流。
     */
    public InputStream getStream() {

        return (this.input);

    }


    /**
     * 设置与此 defaultPackage.Request 关联的输入流。
     *
     * @param input 输入流
     */
    public void setStream(InputStream input) {

        this.input = input;

    }

//TODO 组件未实现
    /**
     *返回正在处理此 defaultPackage.Request 的封装器。
     */
//    public Wrapper getWrapper() {
//
//        return (this.wrapper);
//
//    }


    /**
     * 设置正在处理这个 defaultPackage.Request 的 Wrapper。
     * 一旦识别出合适的 Wrapper，必须立即调用这个方法，并且
     * 在 defaultPackage.Request 最终传递给应用程序 servlet 之前调用。
     *
     * @param wrapper 新关联的 Wrapper
     */
//    public void setWrapper(Wrapper wrapper) {
//
//        this.wrapper = wrapper;
//
//    }


    /**
     * 将一个区域设置添加到此defaultPackage.Request的首选区域设置集合中。
     * 第一个添加的区域设置将是getLocales()返回的第一个区域设置。
     *
     * @param locale 新的首选区域设置
     */
    public void addLocale(Locale locale) {

        synchronized (locales) {
            locales.add(locale);
        }

    }


    /**
     * 创建并返回一个 ServletInputStream 以读取与此 defaultPackage.Request 相关的内容。
     * 默认实现创建一个与此请求相关的 RequestStream 实例，但如有必要，可以覆盖此实现。
     *
     * @throws IOException 如果发生输入/输出错误
     */
    public ServletInputStream createInputStream() throws IOException {
//TODO
        //    return (new RequestStream(this));

        return null;
    }


    /**
     * 执行任何必要的操作以刷新并关闭输入流或读取器，操作应为单个操作。
     *
     * @throws IOException if an input/output error occurs
     */
    public void finishRequest() throws IOException {

        // 如果已获取 Reader，关闭
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException _) {
                ;
            }
        }

        // 如果已经获取了ServletInputStream，则关闭
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException _) {
                ;
            }
        }

// 并不处理底层输入流（可能来自套接字）

    }


    /**
     * 对象重用
     */
    public void recycle() {
//TODO
        attributes.clear();
        authorization = null;
        characterEncoding = null;
        // 回收时不重置连接器
        contentLength = -1;
        contentType = null;
        //context = null;
        input = null;
        locales.clear();
        notes.clear();
        protocol = null;
        reader = null;
        remoteAddr = null;
        remoteHost = null;
        response = null;
        scheme = null;
        secure = false;
        serverName = null;
        serverPort = -1;
        socket = null;
        stream = null;
        //wrapper = null;

    }


    /**
     * 设置与此 defaultPackage.Request 相关的内容长度。
     *
     * @param length 内容长度
     */
    public void setContentLength(int length) {

        this.contentLength = length;

    }


    /**
     * 设置与这个 defaultPackage.Request 关联的内容类型（可选的字符编码）。
     * 例如，
     * <code>text/html; charset=ISO-8859-4</code>。
     *
     * @param type 内容类型
     */
    public void setContentType(String type) {

        this.contentType = type;
        if (type.indexOf(';') >= 0)
            characterEncoding = RequestUtil.parseCharacterEncoding(type);

    }


    /**
     * 设置与此 defaultPackage.Request 关联的协议名称和版本。
     *
     * @param protocol 协议名称和版本
     */
    public void setProtocol(String protocol) {

        this.protocol = protocol;

    }


    /**
     * 设置与此 defaultPackage.Request 关联的远程客户端的 IP 地址。
     *
     * @param remoteAddr 远程 IP 地址
     */
    public void setRemoteAddr(String remoteAddr) {

        this.remoteAddr = remoteAddr;

    }


    /**
     * 设置与此 defaultPackage.Request 关联的远程客户端的完全限定名。
     *
     * @param remoteHost 远程主机名
     */
    public void setRemoteHost(String remoteHost) {

        this.remoteHost = remoteHost;

    }


    /**
     * Set the name of the scheme associated with this request.  Typical values
     * are <code>http</code>, <code>https</code>, and <code>ftp</code>.
     *
     * @param scheme The scheme
     */
    public void setScheme(String scheme) {

        this.scheme = scheme;

    }


    /**
     * 设置 <code>isSecure()</code> 在 defaultPackage.Request 中返回的值。
     *
     * @param secure isSecure 值
     */
    public void setSecure(boolean secure) {

        this.secure = secure;

    }


    /**
     * 设置处理此请求的服务器（虚拟主机）名称。
     *
     * @param name 服务器名称
     */
    public void setServerName(String name) {

        this.serverName = name;

    }


    /**
     * 设置服务器处理此请求的端口号。
     *
     * @param port 服务器端口
     */
    public void setServerPort(int port) {

        this.serverPort = port;

    }


    // ServletRequest Methods


    /**
     * 如果指定的请求属性存在，则返回该属性；否则返回
     * <code>null</code>。
     *
     * @param name 要返回的请求属性的名称
     */
    public Object getAttribute(String name) {

        synchronized (attributes) {
            return (attributes.get(name));
        }

    }


    /**
     * 返回该 defaultPackage.Request 的所有请求属性的名称，如果没有则返回一个空的 <code>Enumeration</code>。
     */
    public Enumeration getAttributeNames() {

        synchronized (attributes) {
            return (new Enumerator(attributes.keySet()));
        }

    }


    /**
     * 返回此 defaultPackage.Request 的字符编码。
     */
    public String getCharacterEncoding() {

        return (this.characterEncoding);

    }


    /**
     * 返回这个defaultPackage.Request的内容长度。
     */
    public int getContentLength() {

        return (this.contentLength);

    }


    /**
     * 返回 defaultPackage.Request 的内容类型。
     */
    public String getContentType() {

        return (contentType);

    }


    /**
     * 返回此 defaultPackage.Request 的 servlet 输入流。默认实现返回由
     * <code>createInputStream()</code> 创建的 servlet 输入流。
     *
     * @throws IllegalStateException 如果 <code>getReader()</code> 已经被调用
     * @throws IOException           如果发生IO错误
     */
    public ServletInputStream getInputStream() throws IOException {

        if (reader != null) throw new IllegalStateException();

        if (stream == null) {
            stream = createInputStream();
        }
        return (stream);
    }


    /**
     * 返回客户端将接受内容的首选语言环境，
     * 基于遇到的第一个 <code>Accept-Language</code> 头的值。
     * 如果请求未指定首选语言，则返回服务器的默认语言环境。
     */
    public Locale getLocale() {

        synchronized (locales) {
            if (!locales.isEmpty())
                return ((Locale) locales.get(0));
            else
                return (defaultLocale);
        }

    }


    /**
     * 返回客户端接受的首选语言环境集，
     * 基于遇到的任何 <code>Accept-Language</code> 头的值。
     * 如果请求未指定首选语言，则返回服务器的默认语言环境。
     */
    public Enumeration getLocales() {

        synchronized (locales) {
            if (!locales.isEmpty())
                return (new Enumerator(locales));
        }
        ArrayList results = new ArrayList();
        results.add(defaultLocale);
        return (new Enumerator(results));

    }


    /**
     * 返回指定请求参数的值（如果有）；
     * 否则，返回 <code>null</code>。
     * 如果定义了多个值，仅返回第一个。
     *
     * @param name 期望的请求参数的名称
     */
    public abstract String getParameter(String name);


    /**
     * 返回此请求参数的<code>Map</code>。
     * defaultPackage.Request参数是与请求一起发送的额外信息。
     * 对于HTTP Servlet，参数包含在查询字符串或提交的表单数据中。
     *
     * @return 一个 <code>Map</code>，其键为参数名称，值为参数值。
     */
    public abstract Map getParameterMap();


    /**
     * 返回此请求定义的所有请求参数的名称。
     */
    public abstract Enumeration getParameterNames();


    /**
     * 如果有，请返回指定请求参数的定义值；否则返回 <code>null</code>。
     *
     * @param name 期望的请求参数的名称
     */
    public abstract String[] getParameterValues(String name);


    /**
     * 返回用于创建此 defaultPackage.Request 的协议和版本。
     */
    public String getProtocol() {

        return (this.protocol);

    }


    /**
     * 读取包装输入流的reader，以获取此 defaultPackage.Request。
     * 默认实现会在通过 <code>createInputStream()</code>
     * 返回的 servlet 输入流周围包装一个 <code>BufferedReader</code>。
     *
     * @throws IllegalStateException 如果 <code>getInputStream()</code>已经被调用过
     * @throws IOException           如果发生IO错误
     */
    public BufferedReader getReader() throws IOException {
        if (stream != null) throw new IllegalStateException();
        if (reader == null) {
            String encoding = getCharacterEncoding();
            if (encoding == null)
                encoding = "ISO-8859-1";
            InputStreamReader isr =
                    new InputStreamReader(createInputStream(), encoding);
            reader = new BufferedReader(isr);
        }
        return (reader);
    }


    /**
     * 返回指定虚拟路径的真实路径。
     *
     * @param path 要转换的路径
     * @deprecated 从 Java Servlet API 2.1 版本开始，请使用
     * <code>ServletContext.getRealPath()</code>。
     */
    //TODO
    public String getRealPath(String path) {
        return null;

    }


    /**
     * 返回发起此 defaultPackage.Request 的远程 IP 地址。
     */
    public String getRemoteAddr() {

        return (this.remoteAddr);

    }


    /**
     * 返回发起这个 defaultPackage.Request 的远程主机名。
     */
    public String getRemoteHost() {

        return (this.remoteHost);

    }


    /**
     * 返回一个 RequestDispatcher，该
     * dispatcher 包装了位于指定路径的资源，
     * 该路径可以被解释为相对于当前请求路径。
     *
     * @param path 要包装的资源路径
     */
    public abstract RequestDispatcher getRequestDispatcher(String path);


    /**
     * 返回用于创建此 defaultPackage.Request 的Scheme。
     */
    public String getScheme() {

        return (this.scheme);

    }


    /**
     * 返回响应此 defaultPackage.Request 的服务器名称。
     */
    public String getServerName() {

        return (this.serverName);

    }


    /**
     * 返回响应此 defaultPackage.Request 的服务器端口。
     */
    public int getServerPort() {

        return (this.serverPort);

    }


    /**
     * 请求是否在安全连接上接收
     */
    public boolean isSecure() {

        return (this.secure);

    }


    /**
     * 则将其移除指定的请求属性。
     *
     * @param name 要移除的请求属性的名称
     */
    public void removeAttribute(String name) {

        synchronized (attributes) {
            attributes.remove(name);
        }

    }


    /**
     * 将指定的请求属性设置为指定的值。
     *
     * @param name  要设置的请求属性名称
     * @param value 相关联的值
     */
    public void setAttribute(String name, Object value) {

        if (name == null) throw new IllegalArgumentException();
        if (value == null) {
            removeAttribute(name);
            return;
        }
        synchronized (attributes) {
            attributes.put(name, value);
        }

    }


    /**
     * 覆盖此请求正文中使用的字符编码名称。
     * 此方法必须在读取请求参数或使用 <code>getReader()</code>
     * 读取输入之前调用。
     * <p>
     * 　　　* @param enc 要使用的字符编码
     *
     * @throws UnsupportedEncodingException 如果指定的编码不被支持
     */
    public void setCharacterEncoding(String enc)
            throws UnsupportedEncodingException {

        // 确保指定的编码有效
        byte[] buffer = new byte[1];
        buffer[0] = (byte) 'a';
        String dummy = new String(buffer, enc);
        if (dummy.equals("a")) this.characterEncoding = enc;
        else throw new UnsupportedEncodingException(enc);
    }


}
