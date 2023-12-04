package aoc2023.day3

data class Number(val value: Int, val adjacentChars: Set<Char>) {
	fun adjacentContainsSymbol() = adjacentChars.any { !it.toString().matches(Regex("^[\\d.]$")) }
}

infix fun IntRange.bounds(integer: Int) : Int =
	if (integer < first)
		first
	else if (integer > last)
		last
	else integer

data class EngineSchematic(val numbers: List<Pair<Pair<IntRange, Int>, Number>>, val `⭐s`: List<Pair<Int, Int>>) {
		companion object {
			fun fromLines(lines: List<String>) : EngineSchematic {
				val lineHeightIndexRange = lines.indices
				val allNumbers = mutableListOf<Pair<Pair<IntRange, Int>, Number>>()
				val `all⭐s` = mutableListOf<Pair<Int, Int>>()
				for ((index, line) in lines.withIndex()) {
					val integers = Regex("\\d+").findAll(line).map { it.range to it.value.toInt() }.toList()
					val lineWidthIndexRange = line.indices
					val numbers = integers.map {
						(it.first to index) to
						Number(
							value = it.second,
							adjacentChars = it.first.flatMap { i ->
								val adjacentChars = mutableSetOf<Char>()
								if (lineHeightIndexRange bounds (index - 1) < index) {
									adjacentChars.addAll(
										setOf(
											lines[index - 1][lineWidthIndexRange bounds (i - 1)],
											lines[index - 1][i],
											lines[index - 1][lineWidthIndexRange bounds (i + 1)],
										)
									)
								}
								if (lineHeightIndexRange bounds (index + 1) > index) {
									adjacentChars.addAll(
										setOf(
											lines[index + 1][lineWidthIndexRange bounds (i - 1)],
											lines[index + 1][i],
											lines[index + 1][lineWidthIndexRange bounds (i + 1)],
										)
									)
								}
								if (i == it.first.first && i > 0) {
									adjacentChars.add(line[i - 1])
								}
								if (i == it.first.last && i < lineWidthIndexRange.last - 1) {
									adjacentChars.add(line[i + 1])
								}
								return@flatMap adjacentChars
							}.toSet()
						)
					}
					val `⭐s` = line.withIndex().filter { (_, v) -> v == '*' }.map { (j, _) -> (j to index) }
					allNumbers.addAll(numbers)
					`all⭐s`.addAll(`⭐s`)
				}
				return EngineSchematic(
					numbers = allNumbers,
					`⭐s` = `all⭐s`
				)
			}
		}
}
