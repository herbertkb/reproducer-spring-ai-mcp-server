# Reproducer for `McpClient` `CallToolRequest` arguments are received as `null` on the server side.

I'm trying to create a very simple Spring AI MCP Server. 

If I run it with `mvn spring-boot:run` and test it with the MCP Inspector, it works fine. 

But if I try to unit test it in the same way as shown in [this blog](https://spring.io/blog/2025/09/16/spring-ai-mcp-intro-blog#using-the-mcp-server) 
and [this example](https://github.com/spring-projects/spring-ai-examples/blob/main/model-context-protocol/mcp-annotations/mcp-annotations-server/src/test/java/org/springframework/ai/mcp/sample/client/SampleClient.java), 
the argument received on the server side in the tool method is `null`. 

```java
@Test
void toolResult() {

    var client = McpClient
            .sync(HttpClientStreamableHttpTransport.builder("http://localhost:" + port).build())
            .build();

    client.initialize();

    assertEquals(2, client.callTool(new CallToolRequest("countEs", Map.of("word", "elephant"))));

    client.closeGracefully();
}
```


```
2026-03-06T16:14:05.448-05:00  INFO 298150 --- [o-auto-1-exec-4] com.example.reproducer.CountEsTool       : word=null
```
