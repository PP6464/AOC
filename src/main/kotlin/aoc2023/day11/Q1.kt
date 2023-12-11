package aoc2023.day11

import java.io.File

fun main() {
	val lines = File("assets/2023/day11/input.txt").readLines()
	println(Universe(lines).shortestDistances.sum())
}