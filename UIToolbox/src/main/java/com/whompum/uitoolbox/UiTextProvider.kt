package com.whompum.uitoolbox

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.annotation.Size
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import com.whompum.models.Day
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Utility class for dealing with [strings.xml]
 * Exposing simple proxy methods to clients, avoiding redundant
 * logic to fetch / format strings
 */
class UiTextProvider @Inject internal constructor(ctx: Resources) {

    init {
        attach(ctx)
    }

    var res: WeakReference<Resources>? = null

    /**
     * Attaches a [Context] object to this class
     */
    private fun attach(ctx: Resources) {
        res = WeakReference(ctx)
    }

    /**
     * Resolves a [String] from its Reference id
     */
    fun get(@StringRes id: Int): String? = res?.get()?.getString(id)

    /**
     * Resolves the string representation of any given day of the week
     * based on the passed in [Day] constant.
     */
    fun getDay(day: Day): String? =
        when (day) {
            Day.Monday -> get(R.string.monday)
            Day.Tuesday -> get(R.string.tuesday)
            Day.Wendesday -> get(R.string.wednesday)
            Day.Thursday -> get(R.string.thursday)
            Day.Friday -> get(R.string.friday)
            Day.Saturday -> get(R.string.saturday)
            Day.Sunday -> get(R.string.sunday)
            else -> null
        }

    /**
     * Returns a [String] representation of multiple days
     * in the form of "%N$s . %N+1$s". E.g.
     * "Monday . Tuesday"
     * Due to the concerns of responsibility, this method does no sanitization on the given
     * arguments and their chronological order, that should be by the client.
     */
    fun getDays(@Size(min = 1, max = 7) vararg day: Day) = if (day.size == 1) getDay(day[0]) else
        String.format(
            getFormatStringFromDayCount(day.size),
            *day.map { getDay(it) }.toTypedArray()
        )

    /**
     * Returns a [String] representation of a single day. This will be the first letter
     * of a day. E.g. getDaySymbol(Day.Monday) == M
     */
    fun getDaySymbol(day: Day): String {
        return getDay(day)!!.substring(0..0)
    }

    /**
     * Resolves the count based on the supplied number in the form of "(N / 20)"
     */
    fun getCounterStringRep(@IntRange(from = 0, to = 20) cnt: Int) =
        String.format(get(R.string.title_count_formatter)!!, cnt)

    /**
     * Returns a [String] representation of a percentage value between
     * zero and one - hundred
     */
    @SuppressLint("Range")
    fun getPercentageString(@FloatRange(from = 0.0, to = 1.0) value: Float): String {
        var strPercent = ""

        val wholeNum = (value * 100).toInt()

        // Isolation strings only. E.g. Those whose string values are one word only
        if (wholeNum <= 19 || (wholeNum % 10 == 0 && wholeNum != 100)) //Less than / equal to nineteen, divisible by ten but not 100.
            strPercent = String.format(
                get(R.string.isolated_pebble_value)!!,
                getIsolatedStringRepresentation(wholeNum)!!
            )
        else if (wholeNum <= 100 && (wholeNum % 10 != 0 || wholeNum == 100)) { //Less than or equal to 100, not divisible by ten unless is 100
            val compound = getCompoundStringRepresentation(wholeNum)
            strPercent = String.format(
                get(R.string.compound_pebble_value)!!,
                compound.first,
                compound.second
            )
        }

        return strPercent
    }

    /**
     * Resolves a [String] with the proper amount of format expressions
     * for formatting multiple days in a single string
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getFormatStringFromDayCount(@Size(min = 2, max = 7) cnt: Int): String =
        when (cnt) {
            2 -> get(R.string.two_day_formatter)!!
            3 -> get(R.string.three_day_formatter)!!
            4 -> get(R.string.four_day_formatter)!!
            5 -> get(R.string.five_day_formatter)!!
            6 -> get(R.string.six_day_formatter)!!
            7 -> get(R.string.seven_day_formatter)!!
            else -> ""
        }

    /**
     * Returns a [String] representation of a number / percentage whose value can be
     * expressed with only one word. E.g. 1 / 0.1 = "One"
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getIsolatedStringRepresentation(@IntRange(from = 0, to = 20) value: Int) =
        when (value) {
            0 -> get(R.string.zero)
            1 -> get(R.string.one)
            2 -> get(R.string.two)
            3 -> get(R.string.three)
            4 -> get(R.string.four)
            5 -> get(R.string.five)
            6 -> get(R.string.six)
            7 -> get(R.string.seven)
            8 -> get(R.string.eight)
            9 -> get(R.string.nine)
            10 -> get(R.string.ten)
            11 -> get(R.string.eleven)
            12 -> get(R.string.twelve)
            13 -> get(R.string.thirteen)
            14 -> get(R.string.fourteen)
            15 -> get(R.string.fifteen)
            16 -> get(R.string.sixteen)
            17 -> get(R.string.seventeen)
            18 -> get(R.string.eighteen)
            19 -> get(R.string.nineteen)
            20 -> get(R.string.twenty)
            30 -> get(R.string.thirty)
            40 -> get(R.string.fourty)
            50 -> get(R.string.fifty)
            60 -> get(R.string.sixty)
            70 -> get(R.string.seventy)
            80 -> get(R.string.eighty)
            90 -> get(R.string.ninety)
            else -> ""
        }

    /**
     * Returns a [String] representation of a number / percentage whose value is
     * represented by at least two words. E.g. 22 / 0.22 = "TwentyTwo"
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getCompoundStringRepresentation(
        @IntRange(
            from = 21,
            to = 100
        ) value: Int
    ): Pair<String, String> {
        //Split the value into ones and tens and return the proper value from each category

        var firstValue: String? = null
        var secondValue: String? = null

        if (value in 21..29) {
            firstValue = get(R.string.twenty)
            secondValue = getIsolatedStringRepresentation(value - 20)
        } else if (value in 31..39) {
            firstValue = get(R.string.thirty)
            secondValue = getIsolatedStringRepresentation(value - 30)
        } else if (value in 41..49) {
            firstValue = get(R.string.fourty)
            secondValue = getIsolatedStringRepresentation(value - 40)
        } else if (value in 51..59) {
            firstValue = get(R.string.fifty)
            secondValue = getIsolatedStringRepresentation(value - 50)
        } else if (value in 61..69) {
            firstValue = get(R.string.sixty)
            secondValue = getIsolatedStringRepresentation(value - 60)
        } else if (value in 71..79) {
            firstValue = get(R.string.seventy)
            secondValue = getIsolatedStringRepresentation(value - 70)
        } else if (value in 81..89) {
            firstValue = get(R.string.eighty)
            secondValue = getIsolatedStringRepresentation(value - 80)
        } else if (value in 91..99) {
            firstValue = get(R.string.ninety)
            secondValue = getIsolatedStringRepresentation(value - 90)
        } else if (value == 100) {
            firstValue = get(R.string.one)
            secondValue = get(R.string.hundred)
        } else {
            firstValue = ""
            secondValue = ""
        }

        return Pair(firstValue!!, secondValue!!)
    }

    /**
     * Clears the [Context] Reference from this classes heap
     */
    fun clear() {
        res?.clear()
    }
}
