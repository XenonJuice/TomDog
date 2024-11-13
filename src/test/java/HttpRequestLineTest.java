// 为测试Target_Function编写表驱动单元测试用例

import http.HttpRequestLine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class HttpRequestLineTest {
    @Test
    public void testHttpRequestLine01() {
        char[] myUri = "/examples/servlets/servlet/doTask".toCharArray();
        HttpRequestLine httpRequestLine = new HttpRequestLine();
        httpRequestLine.uri = myUri;
        httpRequestLine.uriEnd = myUri.length;
        int indexOf = httpRequestLine.indexOf("servlets".toCharArray());
        Assert.assertEquals(10, indexOf);



    }

}
