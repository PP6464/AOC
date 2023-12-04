package aoc2023.day4

data class Card(val winningNumbers: List<Int>, val cardNumbers: List<Int>) {
    val value: Int
        get() {
            var numberOfMatches = 0
            for (winningNumber in winningNumbers) {
                for (cardNumber in cardNumbers) {
                    if (winningNumber == cardNumber) {
                        if (numberOfMatches == 0) {
                            numberOfMatches++
                        } else {
                            numberOfMatches *= 2
                        }
                    }
                }
            }
            return numberOfMatches
        }
}

fun cardsFromLines(lines: List<String>) : List<Card> {
    val cards = mutableListOf<Card>()
    for (line in lines) {
        val winningNumbers = line
            .split(": ")
            .last()
            .split(" | ")
            .first()
            .chunked(3)
            .map(String::trim)
            .map(String::toInt)
        val cardNumbers = line
            .split(": ")
            .last()
            .split(" | ")
            .last()
            .chunked(3)
            .map(String::trim)
            .map(String::toInt)
        cards.add(Card(winningNumbers, cardNumbers))
    }
    return cards
}
