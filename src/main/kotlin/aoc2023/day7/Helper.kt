package aoc2023.day7

val cardOrder = listOf("2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K", "A")

enum class HandType {
	HIGH_CARD,
	ONE_PAIR,
	TWO_PAIR,
	THREE_OF_A_KIND,
	FULL_HOUSE,
	FOUR_OF_A_KIND,
	FIVE_OF_A_KIND;
}

data class Hand(val bid : Int, val cards : String) : Comparable<Hand> {
	private val differentCards = cards.toCharArray().toSet()
	
	val type : HandType
		get() {
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
		}
	
	fun value(rank : Int) = rank * bid
	override fun compareTo(other : Hand) : Int {
		return if (type == other.type) {
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
		} else if (type > other.type) 1
		else -1
	}
	
	companion object {
		fun fromLines(lines : List<String>) : List<Hand> {
			val res = mutableListOf<Hand>()
			for (line in lines) {
				val (cards, bidStr) = line.split(" ")
				val bid = bidStr.toInt()
				res += Hand(cards = cards, bid = bid)
			}
			return res
		}
	}
}