package aoc2023.day11

import java.io.File

fun main() {
	val lines = File("assets/2023/day11/input.txt").readLines()
	print(Universe(lines).shortestDistances(2).sum())
}