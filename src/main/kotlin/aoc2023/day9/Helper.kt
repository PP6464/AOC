package aoc2023.day9

data class NumberSequence(val elements: List<Int>) {
	private val changeSequence = if (elements.all { it == 0 }) elements else elements.zipWithNext(Int::minus).map(Int::unaryMinus)
	fun findNext() : Int {
		if (elements.all { it == 0 }) return 0
		return NumberSequence(changeSequence).findNext() + elements.last()
	}
	
	fun findPrev() : Int {
		if (elements.all { it == 0 }) return 0
		return elements.first() - NumberSequence(changeSequence).findPrev()
	}
	
	companion object {
		fun fromLines(lines: List<String>) : List<NumberSequence> {
			val sequences = mutableListOf<NumberSequence>()
			for (line in lines) {
				sequences += NumberSequence(
					elements = line.split(" ").map(String::toInt)
				)
			}
			return sequences
		}
	}
}
