package aoc2023.day11

data class Point(val x: Int, val y: Int)

fun <T> List<List<T>>.getAtPoint(point : Point) : T {
	return this[point.y][point.x]
}

fun String.copy(n : Int) : String {
	val sb = StringBuilder()
	repeat(n) {
		sb.append(this)
	}
	return sb.toString()
}

data class Universe(val lines: List<String>) {
	val expanded: List<String>
		get() {
			val emptyColumnsAt = (lines[0].indices).filter { !lines.any { l -> l[it] == '#' } }
			val emptyRowsAt = lines.indices.filter { lines[it].all { x -> x == '.' } }
			val linesCopy = lines.toMutableList()
			for ((iterations, index) in emptyRowsAt.withIndex()) {
				linesCopy.add(index + iterations, ".".copy(lines[0].length))
			}
			for ((iterations, index) in emptyColumnsAt.withIndex()) {
				repeat(linesCopy.size) {
					linesCopy[it] = linesCopy[it].toMutableList().apply { add(index + iterations, '.') }.joinToString("")
				}
			}
			return linesCopy
		}
}