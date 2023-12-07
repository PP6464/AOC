package aoc2023.day7

val cardOrder = listOf("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")

enum class HandType {
	HIGH_CARD,
	ONE_PAIR,
	TWO_PAIR,
	THREE_OF_A_KIND,
	FULL_HOUSE,
	FOUR_OF_A_KIND,
	FIVE_OF_A_KIND;
}

data class Hand(val bid : Int, val cards : String, val part : Int = 1) : Comparable<Hand> {
	private val differentCards = cards.toCharArray().toSet()
	
	private fun type() : HandType {
		if (part == 1) {
			return if (cards.all { it == cards.first() }) {
				HandType.FIVE_OF_A_KIND
			} else if (
				differentCards.size == 2 &&
				(
						cards.count { it == differentCards.elementAt(0) } == 4 ||
								cards.count { it == differentCards.elementAt(1) } == 4
						)
			) {
				HandType.FOUR_OF_A_KIND
			} else if (
				differentCards.size == 2 &&
				(
						cards.count { it == differentCards.elementAt(0) } == 3 ||
								cards.count { it == differentCards.elementAt(1) } == 3
						)
			) {
				HandType.FULL_HOUSE
			} else if (
				differentCards.size == 3 &&
				(
						cards.count { it == differentCards.elementAt(0) } == 3 ||
								cards.count { it == differentCards.elementAt(1) } == 3 ||
								cards.count { it == differentCards.elementAt(2) } == 3
						)
			) {
				HandType.THREE_OF_A_KIND
			} else if (
				differentCards.size == 3 &&
				(
						listOf(
							cards.count { it == differentCards.elementAt(0) },
							cards.count { it == differentCards.elementAt(1) },
							cards.count { it == differentCards.elementAt(2) },
						).count {
							it == 2
						} == 2
						)
			) {
				HandType.TWO_PAIR
			} else if (differentCards.size == 4) HandType.ONE_PAIR
			else HandType.HIGH_CARD
		} else {
			return if (differentCards.contains('J')) {
				if (differentCards.all { char -> char == (differentCards.firstOrNull { it != 'J' } ?: 'J') || char == 'J' }) {
					HandType.FIVE_OF_A_KIND
				} else if (
					differentCards.filter { it.toString() != "J" }.size == 2 && differentCards.filter { it.toString() != "J" }
						.run {
							val partitioned = partition { it == first() }
							return@run partitioned.first.size == 1 || partitioned.second.size == 1
						}
				) {
					HandType.FOUR_OF_A_KIND
				} else if (differentCards.filter { it.toString() != "J" }.size == 2) {
					HandType.FULL_HOUSE
				} else if (differentCards.filter { it.toString() != "J" }.size == 3) {
					HandType.THREE_OF_A_KIND
				} else {
					HandType.ONE_PAIR
				}
			} else {
				if (cards.all { it == cards.first() }) {
					HandType.FIVE_OF_A_KIND
				} else if (
					differentCards.size == 2 &&
					(
							cards.count { it == differentCards.elementAt(0) } == 4 ||
									cards.count { it == differentCards.elementAt(1) } == 4
							)
				) {
					HandType.FOUR_OF_A_KIND
				} else if (
					differentCards.size == 2 &&
					(
							cards.count { it == differentCards.elementAt(0) } == 3 ||
									cards.count { it == differentCards.elementAt(1) } == 3
							)
				) {
					HandType.FULL_HOUSE
				} else if (
					differentCards.size == 3 &&
					(
							cards.count { it == differentCards.elementAt(0) } == 3 ||
									cards.count { it == differentCards.elementAt(1) } == 3 ||
									cards.count { it == differentCards.elementAt(2) } == 3
							)
				) {
					HandType.THREE_OF_A_KIND
				} else if (
					differentCards.size == 3 &&
					(
							listOf(
								cards.count { it == differentCards.elementAt(0) },
								cards.count { it == differentCards.elementAt(1) },
								cards.count { it == differentCards.elementAt(2) },
							).count {
								it == 2
							} == 2
							)
				) {
					HandType.TWO_PAIR
				} else if (differentCards.size == 4) HandType.ONE_PAIR
				else HandType.HIGH_CARD
			}
		}
	}
	
	fun value(rank : Int) = rank * bid
	private fun jValue() : Char {
		val charCount = differentCards.filterNot { it == 'J' }.map { it to cards.count { i -> i == it } }
		return try {
			charCount.find { it == charCount.maxBy { j -> j.second } }!!.first
		} catch (e : Throwable) {
			'A'
		}
	}
	
	override fun compareTo(other : Hand) : Int {
		return if (type() == other.type()) {
			var res = 0
			for (i in 0..4) {
				if (cardOrder.indexOf(cards[i].toString()) > cardOrder.indexOf(other.cards[i].toString())) {
					res = 1
					break
				}
				if (cardOrder.indexOf(cards[i].toString()) < cardOrder.indexOf(other.cards[i].toString())) {
					res = -1
					break
				}
			}
			res
		} else if (type() > other.type()) 1
		else -1
	}
	
	fun compareWith(other : Hand) : Int {
		if (type() > other.type()) return 1
		if (type() < other.type()) return -1
		for (i in 0..4) {
			if (cardOrder.indexOf((if (cards[i] == 'J') jValue() else cards[i]).toString()) > cardOrder.indexOf((if (other.cards[i] == 'J') jValue() else other.cards[i]).toString())) return 1
			if (cardOrder.indexOf((if (cards[i] == 'J') jValue() else cards[i]).toString()) < cardOrder.indexOf((if (other.cards[i] == 'J') jValue() else other.cards[i]).toString())) return -1
			if (cards[i] != 'J' && other.cards[i] == 'J') return 1
			if (cards[i] == 'J' && other.cards[i] != 'J') return -1
		}
		return 0
	}
	
	companion object {
		fun fromLines(lines : List<String>, part : Int = 1) : List<Hand> {
			val res = mutableListOf<Hand>()
			for (line in lines) {
				val (cards, bidStr) = line.split(" ")
				val bid = bidStr.toInt()
				res += Hand(cards = cards, bid = bid, part = part)
			}
			return res
		}
	}
}