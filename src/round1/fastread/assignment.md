# Fast Reading
## Problem Statement

Trading decisions can be challenging, but they have to be quick and precise.
If we take a fraction of a second longer, we might miss out on an opportunity.

In such a fast-paced environment, one of the challenges is to quickly consume market data.
We need to identify if there is a trading opportunity by a glance at the data.

To make the decision, we utilize the following information:

- Max buy price - the highest profitable price that we are willing to pay for the product.
- Min sell price - the lowest profitable price that we are willing to receive for the product.
- Market state data - a JSON encoded string that contains various market information.

The market state can look like this:

```JSON
{"timestamp":0,"book":"GOOG","bids":[{"price":800,"volume":10}],"asks":[{"price":800,"volume":10}]}
```

JSON-formatted market state:
```JSON
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
```

We process market data character by character.
Reading a character is slow and expensive while skipping a character is fast and cheap.

The data format is well predictable so that we can skip a lot of characters for better performance.

There is a profitable trading opportunity if there exists a bid or an ask in the market state which is profitable.

- An ask is profitable when ```max buy price >= ask price```
- A bid is profitable when ```min sell price <= bid price```

We have implemented an efficient reader that processes market state character by character in the following way:

- If the next character can be accurately predicted, it is skipped. Otherwise, the character is read.
- Once a profitable trading opportunity is found, the reader stops. The price of the profitable opportunity should be fully read.
- Once all the bids and asks are processed, the reader concludes there is no profitable opportunity and stops.

Given max buy price, min sell price and market state, find the sequence of read and skip actions produced by the reader.

Your task is to implement method

```JAVA
String fastRead(double maxBuyPrice, double minSellPrice, String marketState)
```

## Output format

The output can look like this:

```
S13 R2 S8 R5 S9 R1 S9 R1 S8 R4
```

- The output is a sequence of actions
- There are two types of actions:
  - Skip: S followed by the number of characters to skip
  - Read: R followed by the number of characters to read
- Two actions need to be separated by a single space
- Two actions of the same type in sequence are accepted. For example S10 S3 will be accepted (which can also be represented as S13)

## Limitations

- 0 < maxBuyPrice < minSellPrice <= 1000
- Market state has JSON-like format:
  - Market state contains 4 key/values in the following order: timestamp, book, bids, asks.
  - The timestamp is an integer number. Its value is not important for the profitability decisions.
  - The book is a string containing uppercase Latin characters and underscores.
  - The bids and asks are two arrays of quotes.
    - Each quote contains price and volume with integer values.
    - The quotes aren't sorted by price nor volume.
  - All the strings in the market state are double-quoted.
  - All the numbers in the market state aren't quoted and have values between 0 and 10000 inclusive.
  - There is no whitespaces in the provided market state.
  - Total length of the market state is no more than 1000 characters.

## Examples
### Example 1

Input:
```
max buy price = 700
min sell price = 900
market state = {"timestamp":0,"book":"AAPL","bids":[],"asks":[{"price":600,"volume":10}]}
```
JSON-formatted market state:
```JSON
{
  "timestamp": 0,
  "book": "AAPL",
  "bids": [],
  "asks": [
    {
      "price": 600,
      "volume": 10
    }
  ]
}
```
Output:

```
actions = S13 R2 S8 R5 S9 R1 S9 R1 S8 R4
```
This translates to:

- skip ```{"timestamp":```
- read ```0,```
- skip ```"book":"```
- read ```AAPL"```
- skip ```,"bids":[```
- read ```]```
- skip ```,"asks":[```
- read ```{```
- skip ```"price":```
- read ```600,```
- conclude there is a profitable opportunity so stop reading (we can buy at a profit)

### Example 2

Input:

```
max buy price = 500
min sell price = 900
market state = {"timestamp":15,"book":"TSLA","bids":[{"price":800,"volume":50},{"price":850,"volume":110}],"asks":[]}
```
JSON-formatted market state:
```JSON
{
  "timestamp": 15,
  "book": "TSLA",
  "bids": [
    {
      "price": 800,
      "volume": 50
    },
    {
      "price": 850,
      "volume": 110
    }
  ],
  "asks": []
}
```
Output:

```
actions = S13 R3 S8 R5 S9 R1 S8 R4 S9 R4 S9 R4 S9 R5 S9 R1
```

This translates to:

- skip ```{"timestamp":```
- read ```15,```
- skip ```"book":"```
- read ```TSLA"```
- skip ```,"bids":[```
- read ```{```
- skip ```"price":```
- read ```800,```
- skip ```"volume":```
- read ```50},```
- skip ```{"price":```
- read ```850,```
- skip ```"volume":```
- read ```110}]```
- skip ```,"asks":[```
- read ```]```
- conclude there is no profitable opportunity so stop reading
