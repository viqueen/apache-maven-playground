package org.viqueen.apache;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamsTest {

    private String randomForOdds(String number) throws Exception {
        int value = Integer.parseInt(number);
        if (value % 2 == 1) {
            return UUID.randomUUID().toString();
        }
        throw new Exception("we're even");
    }

    @Test
    public void testToValueOrNull() {
        List<String> strings = IntStream.range(0, 10)
                .mapToObj(Integer::toString)
                .map(Streams.toValueOrNull(this::randomForOdds))
                .collect(Collectors.toList());

        long nullCount = strings.stream().filter(Objects::isNull).count();
        long nonNullCount = strings.stream().filter(Objects::nonNull).count();

        assertEquals(5, nullCount);
        assertEquals(5, nonNullCount);
    }
}
