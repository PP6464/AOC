package aoc2023.day1

fun Char.digitToIntOrNull() : Int? {
	return try {
		digitToInt()
	} catch (e: Throwable) {
		null
	}
}