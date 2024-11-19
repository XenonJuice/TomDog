# HTTP 响应示例

**成功响应示例**：
```
HTTP/1.1 200 OK                       ← 状态行：HTTP 版本、状态码、状态描述
Content-Type: application/json        ← 响应头：响应体的数据类型
Content-Length: 35                    ← 响应头：响应体的长度

{"message": "defaultPackage.Request successful"}     ← 响应体：响应发送的具体数据
```

**错误响应示例**：
```
HTTP/1.1 404 Not Found                ← 状态行：HTTP 版本、状态码、状态描述
Content-Type: text/html               ← 响应头：响应体的数据类型
Content-Length: 48                    ← 响应头：响应体的长度

<html><body><h1>404 Not Found</h1></body></html> ← 响应体：错误信息页面