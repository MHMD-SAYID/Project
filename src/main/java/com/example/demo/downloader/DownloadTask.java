package com.example.demo.downloader;

import java.io.*;
import java.net.*;
import java.util.concurrent.Callable;

public class DownloadTask implements Callable<Void> {
    private final String url;
    private final String destinationDirectory;

    public DownloadTask(String url, String destinationDirectory) {
        this.url = url;
        this.destinationDirectory = destinationDirectory;
    }

    @Override
    public Void call() throws Exception {
        try {
            // Create the directory if it doesn't exist
            File dir = new File(destinationDirectory);
            if (!dir.exists()) {
                dir.mkdirs(); // Create the directory if it doesn't exist
            }

            // Extract the filename from the URL
            String fileName = url.substring(url.lastIndexOf("/") + 1);

            // Create the file path using the destination directory and file name
            File outputFile = new File(dir, fileName);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
            System.out.println("Downloaded: " + outputFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
