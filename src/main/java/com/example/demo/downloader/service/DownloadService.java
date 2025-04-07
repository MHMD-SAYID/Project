package com.example.demo.downloader.service;

import com.example.demo.downloader.util.DownloadThread;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DownloadService {

    private static final int THREAD_COUNT = 4;

    public void downloadFile(String fileUrl) throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int contentLength = conn.getContentLength();

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        file.setLength(contentLength);
        file.close();

        int partSize = contentLength / THREAD_COUNT;

        for (int i = 0; i < THREAD_COUNT; i++) {
            int startByte = i * partSize;
            int endByte = (i == THREAD_COUNT - 1) ? contentLength - 1 : (startByte + partSize - 1);

            Thread thread = new DownloadThread(fileUrl, fileName, startByte, endByte);
            thread.start();
        }
    }
}
