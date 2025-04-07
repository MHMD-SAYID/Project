/*package com.example.demo.downloader.controller;


import com.example.demo.downloader.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @PostMapping
    public ResponseEntity<String> downloadFile(@RequestParam String url) {
        try {
            downloadService.downloadFile(url);
            return ResponseEntity.ok("Download started successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Download failed: " + e.getMessage());
        }
    }
}

*/
package com.example.demo.downloader.controller;

import com.example.demo.downloader.DownloadTask;
import com.example.demo.downloader.MultiFileDownloader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class DownloadController {

    @GetMapping("/downloadFiles")
    public String downloadFiles(@RequestParam String[] urls) {
        // Create a fixed thread pool for concurrent downloads
        ExecutorService executorService = Executors.newFixedThreadPool(urls.length); // One thread per URL

        for (int i = 0; i < urls.length; i++) {
            String destinationFilePath = "downloaded_file_" + (i + 1) + ".bin"; // Assign unique names
            DownloadTask downloadTask = new DownloadTask(urls[i], destinationFilePath);
            executorService.submit(downloadTask);
        }

        executorService.shutdown();
        return "Download process started!";
    }
}
