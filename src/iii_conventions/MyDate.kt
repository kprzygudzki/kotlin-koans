package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
    operator fun compareTo(other: MyDate): Int {
        val y = this.year.compareTo(other.year)
        return if (y != 0) y else {
            val m = this.month.compareTo(other.month)
            if (m != 0) m else this.dayOfMonth.compareTo(other.dayOfMonth)
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeUnit {
    DAY,
    WEEK,
    YEAR
}

operator fun MyDate.plus(unit: TimeUnit): MyDate = this.addTimeIntervals(unit, 1)

data class TimeInterval(val unit: TimeUnit, val number: Int)

operator fun TimeUnit.times(number: Int): TimeInterval = TimeInterval(this, number)

operator fun MyDate.plus(interval: TimeInterval): MyDate =
        this.addTimeIntervals(interval.unit, interval.number)

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var cursor = start

        override fun hasNext(): Boolean = cursor <= endInclusive

        override fun next(): MyDate {
            val result = cursor
            cursor = cursor.nextDay()
            return result
        }
    }

    operator fun contains(date: MyDate): Boolean {
        return start <= date && date <= endInclusive
    }
}
