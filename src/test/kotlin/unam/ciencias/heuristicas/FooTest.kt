package unam.ciencias.heuristicas

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object FooTest : Spek({
    describe("Calculator") {

        it("1 + 2 = 3") {
            assertEquals(3, 2+1)
        }
    }
})
