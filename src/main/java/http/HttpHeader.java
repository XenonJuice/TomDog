package http;

/**
 * 解析HTTP头。
 *
 * @author LILINJIAN
 * @since 2024/11/14 15:02
 */

public final class HttpHeader {
    public static final int INITIAL_NAME_SIZE = 32;
    public static final int INITIAL_VALUE_SIZE = 64;
    public static final int MAX_NAME_SIZE = 128;
    public static final int MAX_VALUE_SIZE = 4096;

    public HttpHeader() {
        this(new char[INITIAL_NAME_SIZE], 0,
                new char[INITIAL_VALUE_SIZE], 0);
    }

    public HttpHeader(char[] name, int nameEnd, char[] value, int valueEnd) {
        this.name = name;
        this.nameEnd = nameEnd;
        this.value = value;
        this.valueEnd = valueEnd;
    }

    public HttpHeader(String name, String value) {
        this.name = name.toLowerCase().toCharArray();
        this.nameEnd = name.length();
        this.value = value.toCharArray();
        this.valueEnd = value.length();
    }

    public char[] name;
    public int nameEnd;
    public char[] value;
    public int valueEnd;
    private int hashCode = 0;

    /**
     * 重用对象，以提高性能并减少内存分配。
     */
    public void recycle() {
        nameEnd = 0;
        valueEnd = 0;
        hashCode = 0;
    }


    /**
     * 测试头部的名称是否等于给定的字符数组。
     * 所有字符必须已经是小写。
     */
    public boolean equals(char[] buf) {
        return equals(buf, buf.length);
    }


    /**
     * 测试头部的名称是否等于给定的字符数组。
     * 所有字符必须是小写。
     */
    public boolean equals(char[] buf, int end) {
        if (end != nameEnd)
            return false;
        for (int i = 0; i < end; i++) {
            if (buf[i] != name[i])
                return false;
        }
        return true;
    }


    /**
     * 测试头部名称是否等于给定的字符串。
     * 给定的字符串必须由小写字母组成。
     */
    public boolean equals(String str) {
        return equals(str.toCharArray(), str.length());
    }


    /**
     * 测试头的值是否等于给定的字符数组。
     */
    public boolean valueEquals(char[] buf) {
        return valueEquals(buf, buf.length);
    }


    /**
     * 测试值是否等于给定的字符数组。
     */
    public boolean valueEquals(char[] buf, int end) {
        if (end != valueEnd)
            return false;
        for (int i = 0; i < end; i++) {
            if (buf[i] != value[i])
                return false;
        }
        return true;
    }


    /**
     * 测试值是否等于给定的字符串。
     */
    public boolean valueEquals(String str) {
        return valueEquals(str.toCharArray(), str.length());
    }


    /**
     * 测试头部的值是否包含给定的字符数组。
     */
    public boolean valueIncludes(char[] buf) {
        return valueIncludes(buf, buf.length);
    }


    /**
     * 测试头部的值是否包含给定的字符数组。
     */
    public boolean valueIncludes(char[] buf, int end) {
        char firstChar = buf[0];
        int pos = 0;
        while (pos < valueEnd) {
            pos = valueIndexOf(firstChar, pos);
            if (pos == -1) return false;
            if ((valueEnd - pos) < end) return false;
            for (int i = 0; i < end; i++) {
                if (value[i + pos] != buf[i]) break;
                if (i == (end - 1)) return true;
            }
            pos++;
        }
        return false;
    }


    /**
     * 测试头部的值是否包含给定字符串。
     */
    public boolean valueIncludes(String str) {
        return valueIncludes(str.toCharArray(), str.length());
    }


    /**
     * 返回值中字符的索引。
     */
    public int valueIndexOf(char c, int start) {
        for (int i = start; i < valueEnd; i++) {
            if (value[i] == c)
                return i;
        }
        return -1;
    }


    /**
     * 测试头部的名称是否等于给定的头部。
     * 名称中的所有字符必须已经是小写。
     */
    public boolean equals(HttpHeader header)  {
        return (equals(header.name, header.nameEnd));
    }

    /**
     * 测试头的名称和值是否与给定的头部相等。
     * 名称中的所有字符必须已经是小写。
     */
    public boolean headerEquals(HttpHeader header) {
        return (equals(header.name, header.nameEnd))
                && (valueEquals(header.value, header.valueEnd));
    }

    /**
     * 返回哈希码。
     * HttpHeader 对象的哈希码与
     * new String(name, 0, nameEnd).hashCode() 返回的哈希码相同。
     */
    public int hashCode() {
        int h = hashCode;
        if (h == 0) {
            int off = 0;
            char[] val = name;
            int len = nameEnd;
            for (int i = 0; i < len; i++)
                h = 31 * h + val[off++];
            hashCode = h;
        }
        return h;
    }


    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return equals(((String) obj).toLowerCase());
        } else if (obj instanceof HttpHeader) {
            return equals((HttpHeader) obj);
        }
        return false;
    }


}
