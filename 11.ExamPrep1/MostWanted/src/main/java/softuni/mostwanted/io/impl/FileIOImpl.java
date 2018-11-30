package softuni.mostwanted.io.impl;


import softuni.mostwanted.io.interfaces.FileIO;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileIOImpl implements FileIO {
    @Override
    public String read(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }


    @Override
    public void write(String fileContent, String file) throws IOException {
        String path = "/src/main/resources" + file;
        try (OutputStream outputStream = new FileOutputStream(path);
             BufferedWriter bufferedWriter = new BufferedWriter(
                     new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
