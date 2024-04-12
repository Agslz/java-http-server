package com.ags.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String html = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                    <title>Simple Java HTTP Server</title>
                    </head>
                    <body>
                    <h1>This page was served using my simple Java HTTP Server</h1>
                    </body>
                    </html>
                    """;

            final String CRLF = "\n\r"; // 13, 10 ASCII

            String response =
                    //Status Line : HTTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    "HTTP/1.1 200 OK" + CRLF +
                            //Header
                            "Content-Length:" + html.getBytes().length + CRLF +
                            CRLF + html + CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection Processing Finished.");

        } catch (IOException e) {

            LOGGER.error("Problem with communcation", e);

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignored) {
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
