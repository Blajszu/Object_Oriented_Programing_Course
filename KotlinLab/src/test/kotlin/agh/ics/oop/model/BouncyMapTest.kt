package agh.ics.oop.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : FunSpec({

 val width = 5
 val height = 5

 context("place method") {

  test("should place the animal if position is free") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(1, 1))
   shouldNotThrow<Exception> {
    bouncyMap.place(animal)
   }
   bouncyMap.objectAt(Vector2d(1, 1)).get() shouldBe animal
  }

  test("should not place the same animal twice") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(2, 2))
   bouncyMap.place(animal)
   bouncyMap.place(animal) // Animal should not be added again
   bouncyMap.objectAt(Vector2d(2, 2)).get() shouldBe animal
   bouncyMap.getOrderedAnimals().size shouldBe 1
  }

  test("should move animal if position is occupied and find a free position") {
   val bouncyMap = BouncyMap(width, height)
   val animal1 = Animal(Vector2d(1, 1))
   val animal2 = Animal(Vector2d(1, 1))
   bouncyMap.place(animal1)
   bouncyMap.place(animal2)

   bouncyMap.objectAt(Vector2d(1, 1)).get() shouldBe animal1
   bouncyMap.objectAt(animal2.getPosition()).get() shouldBe animal2
  }

  test("should replace an animal if the map is full") {
   val bouncyMap = BouncyMap(width, height)
   val animals = mutableListOf<Animal>()
   for (i in 0 until width * height) {
    val animal = Animal(Vector2d(i % width, i / width))
    animals.add(animal)
    bouncyMap.place(animal)
   }

   val newAnimal = Animal(Vector2d(0, 0))
   bouncyMap.place(newAnimal)

   bouncyMap.getOrderedAnimals().size shouldBe width * height

   bouncyMap.objectAt(newAnimal.getPosition()).get() shouldBe newAnimal
  }
 }

 context("move method") {

  test("should move animal to a valid position") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(1, 1))
   bouncyMap.place(animal)

   // Move animal forward
   bouncyMap.move(animal, MoveDirection.FORWARD)
   bouncyMap.objectAt(Vector2d(1, 2)).get() shouldBe animal
  }

  test("should not move animal outside the map boundaries") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(4, 4))
   bouncyMap.place(animal)

   bouncyMap.move(animal, MoveDirection.FORWARD)
   bouncyMap.objectAt(Vector2d(4, 4)).get() shouldBe animal
  }

  test("should update animal position after moving") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(1, 1))
   bouncyMap.place(animal)

   bouncyMap.move(animal, MoveDirection.RIGHT)
   bouncyMap.objectAt(Vector2d(1, 1)).get() shouldBe animal
   animal.getCurrentOrientation() shouldBe MapDirection.EAST
  }

  test("should update orientation when moving left or right") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(1, 1))
   bouncyMap.place(animal)

   val originalOrientation = animal.getCurrentOrientation()
   bouncyMap.move(animal, MoveDirection.LEFT)
   animal.getCurrentOrientation() shouldNotBe originalOrientation
  }
 }

 context("helper methods") {

  test("should verify if the map position is occupied") {
   val bouncyMap = BouncyMap(width, height)
   val animal = Animal(Vector2d(3, 3))
   bouncyMap.place(animal)

   bouncyMap.isOccupied(Vector2d(3, 3)) shouldBe true
   bouncyMap.isOccupied(Vector2d(2, 2)) shouldBe false
  }

  test("should return the correct bounds of the map") {
   val bouncyMap = BouncyMap(width, height)
   bouncyMap.getCurrentBounds() shouldBe Boundary(Vector2d(0, 0), Vector2d(width - 1, height - 1))
  }
 }
})
