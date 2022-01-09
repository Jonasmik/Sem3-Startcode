package utils;

import dtos.CombinedDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilsTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchFacts() throws IOException, ExecutionException, InterruptedException {

        int counter = 0;

        CombinedDTO combinedDTO = HttpUtils.fetchFacts();

            if (combinedDTO.getCatFact() != null){
                counter++;
            }
            if (combinedDTO.getDogFact() != null){
                counter++;
            }
            if (combinedDTO.getFunFact() != null){
                counter++;
            }
            if (combinedDTO.getNumberFact() != null){
                counter++;
            }
            if (combinedDTO.getRandomFact() != null){
                counter++;
            }

        assertEquals(5, counter);
    }
}