# HTTP 请求示例

```
GET /index.html HTTP/1.1               ← 请求行：请求方法、路径、HTTP 版本
Host: www.example.com                  ← 请求头：指定请求的主机名
User-Agent: Mozilla/5.0                ← 请求头：客户端信息
Accept: text/html                      ← 请求头：客户端接受的内容类型

                                       ← 空行：标志请求头结束
```

```
POST /api/data HTTP/1.1                ← 请求行：请求方法、路径、HTTP 版本
Host: www.example.com                  ← 请求头：指定请求的主机名
Content-Type: application/json         ← 请求头：请求体的内容类型（JSON 格式）
Content-Length: 27                     ← 请求头：请求体的长度（字节数）

{"key": "value", "id": 123}            ← 请求体：请求发送的具体数据
```