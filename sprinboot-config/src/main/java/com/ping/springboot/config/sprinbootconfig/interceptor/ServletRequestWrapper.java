/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springboot.config.sprinbootconfig.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @version $Id ServletRequestWrapper.java, v 1.0 2019-05-14 16:50 zsp $$
 * @author: zhangsp
 */

public class ServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletRequestWrapper.class);

    private final byte[] body;

    public ServletRequestWrapper(HttpServletRequest request) {
        super(request);
        body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
       return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
       final ByteArrayInputStream bais = new ByteArrayInputStream(body);

       return new ServletInputStream() {
           @Override
           public boolean isFinished() {
               return false;
           }

           @Override
           public boolean isReady() {
               return false;
           }

           @Override
           public void setReadListener(ReadListener readListener) {

           }

           @Override
           public int read() throws IOException {
               return bais.read();
           }
       };
    }

    public String getBodyString(final HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine())!= null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("read inputStream error, ", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close inputstream error, ", e);
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("close BufferedReader error, " , e);
                }
            }
        }
        return sb.toString();
    }

    public InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            LOGGER.error("write error, ", e);
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
