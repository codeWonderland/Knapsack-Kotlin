import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as test

class KnapsackTest {

    @DisplayName("Test Input")
    @test fun fillKnapsack() {
        val knapsack = Knapsack("input.txt")

        assertEquals(1480, knapsack.maxValue)
    }
}