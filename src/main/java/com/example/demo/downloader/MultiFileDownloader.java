package com.example.demo.downloader;

import java.util.concurrent.*;

public class MultiFileDownloader {

    public static void main(String[] args) {
        // List of file URLs to download
        String[] fileUrls = {
                "https://speed.hetzner.de/100MB.bin",
                "https://speed.hetzner.de/500MB.bin",
                "https://speed.hetzner.de/1GB.bin"
        };

        // Create a fixed thread pool with the desired number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(3); // Set number of threads to download files concurrently

        // Loop through each URL and create a task for each file download
        for (int i = 0; i < fileUrls.length; i++) {
            String destinationFilePath = "downloaded_file_" + (i + 1) + ".bin"; // Assign a unique filename for each download
            DownloadTask downloadTask = new DownloadTask(fileUrls[i], destinationFilePath);
            executorService.submit(downloadTask);
        }

        // Shut down the executor service after all tasks are submitted
        executorService.shutdown();
        try {
            // Wait for all tasks to finish
            if (!executorService.awaitTermination(60, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        System.out.println("All downloads are completed.");
    }
}
