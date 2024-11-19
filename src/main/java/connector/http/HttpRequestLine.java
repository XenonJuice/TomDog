package connector.http;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 解析HTTP 请求行。在 uri 字符数组中查找特定的字符或子串的位置
 * @author LILINJIAN
 * @since 2024/11/13 15:02
 */
public final class HttpRequestLine {
    /**
     * INITIAL_METHOD_SIZE - 初始方法（例如 GET、POST...）大小
     * INITIAL_URI_SIZE - 初始 URI 大小
     * INITIAL_PROTOCOL_SIZE - 初始协议大小
     * MAX_METHOD_SIZE - 最大方法大小
     * MAX_URI_SIZE - 最大 URI 大小
     * MAX_PROTOCOL_SIZE - 最大协议大小
     * DEFAULT_SIZE_ZERO - 默认大小为 0
     */
    public static final int INITIAL_METHOD_SIZE = 8;
    public static final int INITIAL_URI_SIZE = 64;
    public static final int INITIAL_PROTOCOL_SIZE = 8;
    public static final int MAX_METHOD_SIZE = 1024;
    public static final int MAX_URI_SIZE = 32768;
    public static final int MAX_PROTOCOL_SIZE = 1024;
    public static final int DEFAULT_SIZE_ZERO = 0;

    /**
     * method - 存储 HTTP 请求方法的字符数组
     * methodEnd - 请求方法的结束位置
     * uri - 存储 HTTP 请求 URI 的字符数组
     * uriEnd - 请求 URI 的结束位置
     * protocol - 存储 HTTP 请求协议的字符数组
     * protocolEnd - 请求协议的结束位置
     */
    public char[] method;
    public int methodEnd;
    public char[] uri;
    public int uriEnd;
    public char[] protocol;
    public int protocolEnd;

    public HttpRequestLine() {
        // 无参调用有参来初始化内部变量
        this(new char[INITIAL_METHOD_SIZE],
                DEFAULT_SIZE_ZERO,
                new char[INITIAL_URI_SIZE], 0,
                new char[INITIAL_PROTOCOL_SIZE],
                DEFAULT_SIZE_ZERO);
    }

    public HttpRequestLine(char[] method, int methodEnd,
                           char[] uri, int uriEnd,
                           char[] protocol, int protocolEnd) {
        this.method = method;
        this.methodEnd = methodEnd;
        this.uri = uri;
        this.uriEnd = uriEnd;
        this.protocol = protocol;
        this.protocolEnd = protocolEnd;
    }

    /**
     * 重用对象，以提高性能并减少内存分配。
     */
    public void recycle() {
        this.methodEnd = 0;
        this.uriEnd = 0;
        this.protocolEnd = 0;
    }

    /**
     * 在 uri 中查找字符数组 buf 的起始索引位置
     * 调用重载方法 indexOf(char[] buf, int end)
     *
     * @param buf 要查找的字符数组
     */
    public int indexOf(char[] buf) {
        return indexOf(buf, buf.length);
    }

    /**
     * 在 uri 中查找指定长度 end 的字符数组 buf 的起始索引位置。
     * 返回 -1 表示未找到
     *
     * @param buf 要查找的字符数组
     * @param end 要查找的字符数组的长度
     */

    @Contract(pure = true)
    public int indexOf(char @NotNull [] buf, int end) {
        char firstChar = buf[0];
        int pos = 0;
        while (pos < uriEnd) {
            pos = indexOf(firstChar, pos);
            if (pos == -1) return -1;
            if ((uriEnd - pos) < end) return -1;
            for (int i = 0; i < end; i++) {
                if (uri[i + pos] != buf[i]) break;
                if (i == (end - 1)) return pos;
            }
            pos++;
        }
        return -1;
    }

    /**
     * 在 uri 中查找指定字符 str 的起始索引位置。
     * 返回 -1 表示未找到
     *
     * @param str 要查找的字符
     */
    public int indexOf(String str) {
        return indexOf(str.toCharArray(), str.length());
    }

    /**
     * 在 uri 中查找指定字符 c 的起始索引位置。
     * 返回 -1 表示未找到
     *
     * @param c     要查找的字符
     * @param start 要查找的起始位置
     */
    public int indexOf(char c, int start) {
        for (int i = start; i < uriEnd; i++) {
            if (uri[i] == c)
                return i;
        }
        return -1;
    }


    @Deprecated
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("这个类不是拿来这么用的");
    }

    @Deprecated
    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("这个类不是拿来这么用的");
    }

}
