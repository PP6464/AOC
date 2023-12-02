package aoc2023.day1

import java.io.File

fun main() {
	val lines = File("assets/2023/day1/input.txt").readLines()
	var res = 0
	for (line in lines) {
		res += line.firstNotNullOf(Char::digitToIntOrNull) * 10 + line.map(Char::digitToIntOrNull).filterNotNull().last()
	}
	print(res)
}