package com.example.reproducer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class CountEsTool {
    Logger log = LoggerFactory.getLogger(CountEsTool.class);

    @McpTool(name = "countEs", description = "Count the number of the letter 'e' in a word.")
    public int countEs(
            @McpToolParam(description = "word", required = true) String word) {
        log.info("word={}", word);

        int count = (int) List.of(word.split("")).stream()
                    .filter(s -> s.equalsIgnoreCase("e"))
                    .count();

        log.info("word={}, count={}", word, count);
        return count;
    }
}
