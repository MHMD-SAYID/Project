# File Downloader with Multithreading in Spring Boot

This is a Spring Boot application that allows users to download multiple files concurrently using multithreading. The application takes in a list of file URLs and downloads them in parallel, providing an efficient and fast file download experience.

## Features
- **Multithreading**: Utilizes Java's multithreading capabilities to download multiple files at once.
- **Spring Boot**: Built using Spring Boot to create a simple RESTful API.
- **File Download**: Downloads files from provided URLs and saves them with their original names.
- **Configurable Server**: Supports custom server port configuration via `application.properties`.

## Technologies Used
- **Java 17**: The project is built using Java 17, leveraging its new features and improvements.
- **Spring Boot**: Spring Boot is used to create a RESTful web service.
- **Multithreading**: Java's `ExecutorService` is used for concurrent file downloads.

## How to Run
1. Clone the repository:
    ```bash
    git clone https://github.com/MHMD-SAYID/Project.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Project
    ```

3. Install dependencies:
    ```bash
    ./mvnw install
    ```

4. Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

5. Open your browser or Postman and go to `http://localhost:9090`.

## Example Usage
You can send a GET request to download files from multiple URLs:

```http
GET http://localhost:9090/downloadFiles?urls=https://example.com/file1.txt&urls=https://example.com/file2.pdf
