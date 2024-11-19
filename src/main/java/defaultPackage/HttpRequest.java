package defaultPackage;

import java.security.Principal;
import java.util.Locale;
import javax.servlet.http.Cookie;

/**
 * 参考tomcat源码
 * <b>defaultPackage.HttpRequest</b> 是对 <code>HttpServletRequest</code> 的封装，
 * 用于处理请求并生成相应的 <code>defaultPackage.HttpResponse</code>。
 */
public interface HttpRequest extends Request {

    /**
     * 将 Cookie 添加到与此请求关联的 Cookie 集合中。
     */
    void addCookie(Cookie cookie);

    /**
     * 将 Header 添加到与此请求关联的 Header 集合中。
     */
    void addHeader(String name, String value);

    /**
     * 将 Locale 添加到此请求的首选 Locale 集合中。
     */
    void addLocale(Locale locale);

    /**
     * 添加参数名称及其对应的值集到此请求中。
     */
    void addParameter(String name, String[] values);

    /**
     * 清空与此请求关联的 Cookie 集合。
     */
    void clearCookies();

    /**
     * 清空与此请求关联的 Header 集合。
     */
    void clearHeaders();

    /**
     * 清空与此请求关联的 Locale 集合。
     */
    void clearLocales();

    /**
     * 清空与此请求关联的参数集合。
     */
    void clearParameters();

    /**
     * 设置此请求的身份验证类型。
     */
    void setAuthType(String type);

    /**
     * 设置此请求的上下文路径。
     */
    void setContextPath(String path);

    /**
     * 设置此请求的 HTTP 请求方法。
     */
    void setMethod(String method);

    /**
     * 设置此请求的查询字符串。
     */
    void setQueryString(String query);

    /**
     * 设置此请求的路径信息。
     */
    void setPathInfo(String path);

    /**
     * 设置是否通过 Cookie 获取请求的Session ID 标志。
     */
    void setRequestedSessionCookie(boolean flag);

    /**
     * 设置请求的Session ID。
     */
    void setRequestedSessionId(String id);

    /**
     * 设置是否通过 URL 获取请求的Session ID 标志。
     */
    void setRequestedSessionURL(boolean flag);

    /**
     * 设置请求的未解析 URI。
     */
    void setRequestURI(String uri);

    /**
     * 设置解码后的请求 URI。
     */
    void setDecodedRequestURI(String uri);

    /**
     * 获取解码后的请求 URI。
     */
    String getDecodedRequestURI();

    /**
     * 设置此请求的 servlet 路径。
     */
    void setServletPath(String path);

    /**
     * 设置已认证的用户主体。
     */
    void setUserPrincipal(Principal principal);
}
