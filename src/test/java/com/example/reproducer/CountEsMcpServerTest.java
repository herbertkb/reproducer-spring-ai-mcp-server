package com.example.reproducer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.HttpClientStreamableHttpTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.ai.mcp.server.stdio=false"
})
public class CountEsMcpServerTest {

    @LocalServerPort
    private int port;

    @Test
    void toolIsRegistered() {

        var client = McpClient
                .sync(HttpClientStreamableHttpTransport.builder("http://localhost:" + port).build())
                .build();

        client.initialize();

        final var toolsList = client.listTools();
        assertNotNull(toolsList);
        assertEquals(toolsList.tools().size(), 1);
        assertEquals(1, toolsList.tools().stream().filter(tool -> tool.name().equals("countEs")).count());

        client.closeGracefully(); 
    }

    @Test
    void toolResult() {

        var client = McpClient
                .sync(HttpClientStreamableHttpTransport.builder("http://localhost:" + port).build())
                .build();

        client.initialize();

        assertEquals(2, client.callTool(new CallToolRequest("countEs", Map.of("word", "splendiferous"))));

        client.closeGracefully();
    }
}
