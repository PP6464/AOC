package aoc2023.day10

data class Coord(val x: Int, val y: Int)

data class Pipes(lines: List<String>) {
    private val startingCoord = Coord(lines.filter { it.contains("S") }.single().indexOf("S"), lines.withIndex().filter { i, v -> v.contains("S") }.single().index)
    
    fun getCoord(coord: Coord) {
        return lines[y][x]
    }

    fun findLargestLoop() : List<Coord> {

    }
}