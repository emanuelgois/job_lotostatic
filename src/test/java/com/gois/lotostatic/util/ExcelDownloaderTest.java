package com.gois.lotostatic.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpServer;

import static org.assertj.core.api.Assertions.assertThat;

class ExcelDownloaderTest {

    @Test
    void testDownloadExcel_salvaArquivoComSucesso() throws Exception {
        // Cria conteúdo simulado do arquivo
        byte[] fileContent = "Conteúdo do Excel".getBytes();

        // Cria um servidor HTTP local em uma porta livre
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        int port = server.getAddress().getPort();

        // Define o handler que retorna o conteúdo simulado
        server.createContext("/excel.xlsx", exchange -> {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, fileContent.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(fileContent);
            }
        });

        server.start();

        try {
            // URL simulada do arquivo
            String url = "http://localhost:" + port + "/excel.xlsx";
            Path tempFile = Files.createTempFile("test-excel", ".xlsx");

            // Executa o método a ser testado
            File downloaded = ExcelDownloader.downloadExcel(url, tempFile.toString());

            // Verifica
            assertThat(downloaded).exists();
            assertThat(Files.readAllBytes(downloaded.toPath())).isEqualTo(fileContent);

            // Limpa
            Files.deleteIfExists(downloaded.toPath());

        } finally {
            server.stop(0);
        }
    }
}