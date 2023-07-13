# quickfixj-demo

## Des
- 基于 quickfixj 和 quickfixj-spring-boot-starter-examples 实现 Fix 通信示例。
- quickfixj: https://github.com/quickfix-j/quickfixj
- quickfixj-spring-boot-starter-examples: https://github.com/esanchezros/quickfixj-spring-boot-starter-examples

## v1.0.0
- 实现基本客户端服务端通信能力、多session、多协议支持
- 基于 toAdmin、fromAdmin 实现客户端到服务端通信登录验证
- 基于 Application 接口实现自定义 Acceptor、Initiator
- 基于 MessageCracker 实现 Fix协议类型、通信数据类型拆分处理
- 实现 xml 文件字段自定义(登录校验字段 Logon: username、password)
```
xml 文件变更后需要使用 quickfixj 重新构建 quickfixj-messages-all 并引入最新版本、同时更新 resources 下的数据字典 xml
```
