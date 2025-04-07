package com.example.demo.downloader.util;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread extends Thread {

    private final String fileURL;
    private final String fileName;
    private final int startByte;
    private final int endByte;

    public DownloadThread(String fileURL, String fileName, int startByte, int endByte) {
        this.fileURL = fileURL;
        this.fileName = fileName;
        this.startByte = startByte;
        this.endByte = endByte;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(fileURL).openConnection();
            conn.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte);
            conn.connect();

            try (InputStream input = conn.getInputStream();
                 RandomAccessFile outputFile = new RandomAccessFile(fileName, "rw")) {
                outputFile.seek(startByte);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    outputFile.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Downloaded: " + startByte + " - " + endByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
