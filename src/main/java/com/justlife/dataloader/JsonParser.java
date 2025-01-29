package com.justlife.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    private static final String JSON_FILE_PATH = "src/main/java/com/justlife/testData/testdata.json"; // Modify the path as needed

    public static DataLoader loadTestData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Read the JSON file and map it to the BookingData class
        return objectMapper.readValue(new File(JSON_FILE_PATH), DataLoader.class);
    }
}
