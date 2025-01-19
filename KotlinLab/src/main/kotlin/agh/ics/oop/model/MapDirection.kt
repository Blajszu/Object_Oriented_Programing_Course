package agh.ics.oop.model

enum class MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    companion object {
        private val directions = arrayOf(
            Vector2d(0, 1),
            Vector2d(1, 0),
            Vector2d(0, -1),
            Vector2d(-1, 0)
        )

        private val names = arrayOf("Polnoc", "Wschod", "Poludnie", "Zachod")
    }

    override fun toString(): String {
        return names[this.ordinal]
    }

    fun next(): MapDirection {
        return entries[(this.ordinal + 1) % entries.size]
    }

    fun previous(): MapDirection {
        val index = if (this.ordinal - 1 < 0) entries.size - 1 else this.ordinal - 1
        return entries[index]
    }
}
