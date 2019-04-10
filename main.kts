// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any) : String {
    val result = when (arg) {
        "Hello" -> "world"
        "Howdy" -> "Say what?"
        "Bonjour" -> "Say what?"
        0 -> "zero"
        1 -> "one"
        5 -> "low number"
        9 -> "low number"
        else -> "I don't understand"
    }
    return result
}
// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(num1: Int, num2: Int) : Int {
    return num1 + num2
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(num1: Int, num2: Int) : Int {
    return num1 - num2;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(num1: Int, num2: Int, op: (Int, Int) -> Int) : Int {
    return op(num1, num2)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, var age: Int) {
    private var _debugString: String? = null
    public val debugString: String
        get() {
            if (_debugString == null) {
                _debugString = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
            }
            return _debugString ?: throw AssertionError("Set to null by another thread")
        }
}

// write a class "Money"
class Money(val amount : Int, val currency : String) {
    fun convert(newCurrency : String) : Money {
        val result = when (this.currency) {
            "USD" -> when(newCurrency) {
                "EUR" -> Money((this.amount * 1.5).toInt(), "EUR")
                "CAN" -> Money((this.amount * 1.25).toInt(), "CAN")
                "GBP" -> Money(this.amount / 2, "GBP")
                else -> Money(this.amount, "USD")
            }
            "GBP" -> when(newCurrency) {
                "USD" -> Money(this.amount * 2, "USD")
                "CAN" -> Money((this.amount * 2.5).toInt(), "CAN")
                "EUR" -> Money(this.amount * 3, "EUR")
                else -> Money(this.amount, "GBP")
            }
            else -> Money(this.amount, this.currency)
        }
        return result
    }
    operator fun plus(other: Money) : Money {
        if (this.currency != other.currency) {
            val converted = other.convert(this.currency)
            return Money(converted.amount + this.amount, this.currency)
        } else {
            return Money(this.amount + other.amount, this.currency)
        }
    }
}

// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
