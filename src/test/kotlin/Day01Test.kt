import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun testFuel() {
        assertEquals(2, fuel(12))
        assertEquals(2, fuel(14))
        assertEquals(654, fuel(1969))
        assertEquals(33583, fuel(100756))
    }

    @Test
    fun testFuelPlus() {
        assertEquals(2, fuelPlus(14))
        assertEquals(966, fuelPlus(1969))
        assertEquals(50346, fuelPlus(100756))
    }
}
