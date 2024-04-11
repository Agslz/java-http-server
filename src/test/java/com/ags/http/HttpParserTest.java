package com.ags.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeAll() {
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
        httpParser.parseHttpRequest(
                generateValidTestCase()
        );
    }

    private InputStream generateValidTestCase(){
        String rawDataString = """
                GET / HTTP/1.1
                Host: localhost:8080
                User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:124.0) Gecko/20100101 Firefox/124.0
                Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8
                Accept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3
                Accept-Encoding: gzip, deflate, br
                Connection: keep-alive
                Upgrade-Insecure-Requests: 1
                Sec-Fetch-Dest: document
                Sec-Fetch-Mode: navigate
                Sec-Fetch-Site: none
                Sec-Fetch-User: ?1
                """;

        InputStream inputStream = new ByteArrayInputStream(
                rawDataString.getBytes(
                        StandardCharsets.US_ASCII
                )
        );

        return inputStream;
    }
}