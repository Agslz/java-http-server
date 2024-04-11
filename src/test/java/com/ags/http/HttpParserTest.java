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
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidGETTestCase()
            );
        } catch (HttpParsingException e) {
            fail(e);
        }

        assertEquals(request.getMethod(), HttpMethod.GET);
    }

    @Test
    void parseHttpRequestBadMethod1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName1()
            );
          //  fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }

    @Test
    void parseHttpRequestBadMethod2() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName2()
            );
            //  fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }

    @Test
    void parseHttpRequestInvNumItems1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestLineInvNumItems1()
            );
            //fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }

    private InputStream generateValidGETTestCase() {
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

    private InputStream generateBadTestCaseMethodName1() {
        String rawDataString = """
                GET / HTTP/1.1
                Host: localhost:8080
                Accept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3
                """;

        InputStream inputStream = new ByteArrayInputStream(
                rawDataString.getBytes(
                        StandardCharsets.US_ASCII
                )
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName2() {
        String rawDataString = """
                GETTTT / HTTP/1.1
                Host: localhost:8080
                Accept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3
                """;

        InputStream inputStream = new ByteArrayInputStream(
                rawDataString.getBytes(
                        StandardCharsets.US_ASCII
                )
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseRequestLineInvNumItems1() {
        String rawDataString = """
                GET / AAAAAA HTTP/1.1
                Host: localhost:8080
                Accept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3
                """;

        InputStream inputStream = new ByteArrayInputStream(
                rawDataString.getBytes(
                        StandardCharsets.US_ASCII
                )
        );

        return inputStream;
    }

}