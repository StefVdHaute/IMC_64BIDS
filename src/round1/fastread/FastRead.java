package round1.fastread;

import java.util.*;

// This was probably meant to be some sort of parser... Too bad

/* Example of data to parse
{
  "timestamp": 0,
  "book": "GOOG",
  "bids": [
    {
      "price": 800,
      "volume": 10
    }
  ],
  "asks": [
    {
      "price": 800,
      "volume": 10
    }
  ]
}
 */

public class FastRead {
    public String fastRead(double maxBuyPrice, double minSellPrice, String marketState) {
        StringBuilder read = new StringBuilder();

        PrimitiveIterator.OfInt s = marketState.chars().iterator();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // First skip |{"timestamp":|
        read.append("S13 ");

        // Skip first characters
        skip(s, 13);

        // Find how many chars to read
        int i = skipChar(s, ',');

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Read timestamp data, skip |"book":"|
        read.append('R').append(i).append(" S8 ");
        // Skip to book data
        skip(s, 8);

        // Find how many chars to read
        i = skipChar(s, '"');

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Read book data, skip |,"bids":[|
        read.append('R').append(i).append(" S9");

        // Skip to bids data
        skip(s, 9);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Read bids data
        i = readOption(s, read, Comparator.comparingDouble((Double a) -> a), minSellPrice);

        if (i == -1) { // No bids
            read.append(" S9");
            // skip to asks
            skip(s, 9);
        } else { // Profitable opportunity found
            return read.toString();
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Read asks data
        readOption(s, read, Comparator.comparingDouble((Double a) -> a*-1), maxBuyPrice);

        return read.toString();
    }

    // Comparator.comparingInt(i -> ((int) i)*-1)
    private int readOption(PrimitiveIterator.OfInt s, StringBuilder read, Comparator<Double> comp, double toCompare) {
        char rChar = (char) (int) s.next(); // first char after '['

        if (rChar == '{') { // list is not empty
            read.append(" R1 S8"); // Read first char after |[|, skip |"price":|

            while (rChar != ']') { // Check price
                skip(s, 8); // Skip to price data
                String value = readUntilAndSkip(s, ','); // Read the price and skip |,|
                read.append(" R").append(value.length() + 1); // Read the price and |,| (to know when to stop)

                if (comp.compare(Double.parseDouble(value), toCompare) >= 0) { // Profitable opportunity found
                    return 1;
                } else { // No profitable opportunity found
                    read.append(" S9"); // Skip |"volume":|
                    skip(s, 9);

                    int i = skipChar(s, '}'); // find #chars until and including |}|
                    rChar = (char) (int) s.next(); // get next char
                    read.append(" R").append(i + 1);// Read 1 further to check whether list ends or continues
                    if (rChar != ']') {
                        read.append(" S9");
                        s.next();
                    }
                }
            }
        } else /* Should always be the case: if (rChar == ']')*/ { // list is empty
            read.append(" R1");
        }
        return -1;
    }

    private void skip(PrimitiveIterator.OfInt s, int i) {
        for (int j = 0; j < i; j++) {
            s.next();
        }
    }

    private int skipChar(PrimitiveIterator.OfInt s, char c) {
        int i = 1;
        while (c != s.next()) {
            i++;
        }
        return i;
    }

    private String readUntilAndSkip(PrimitiveIterator.OfInt s, char c) {
        StringBuilder value = new StringBuilder();
        char rChar = (char) (int) s.next();

        while (c != rChar) { // Get ask price
            value.append(rChar);
            rChar = (char) (int) s.next();
        }

        return value.toString();
    }
}
