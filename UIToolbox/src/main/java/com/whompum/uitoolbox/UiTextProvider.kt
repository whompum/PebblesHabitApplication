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
 * TODO Setup compound percent helper method
 */
class UiTextProvider @Inject internal constructor(ctx: Resources) {

    init {
        attach(ctx)
    }

    var res: WeakReference<Resources>? = null

    /**
     * Attaches a [Context] object to this class
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun attach(ctx: Resources) {
        clear()
        res = WeakReference(ctx)
    }

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
     * arguments and their chronological order, that should be   by the client.
     */
    fun getDays(@Size(min = 1, max = 7) vararg day: Day) = if (day.size == 1) getDay(day[0])!! else
        String.format(getFormattedDayStringFromCount(day.size), day.map { getDay(it) })

    /**
     * Returns a [String] representation of a single day. This will be the first letter
     * of a day. E.g. getDaySymbol(Day.Monday) == M
     */
    fun getDaySymbol(day: Day): String {
        return getDay(day)!!.substring(0..1)
    }

    /**
     * Resolves the count based on the supplied number in the form of "(N / 20)"
     */
    fun getCounterStringRep(@IntRange(from = 0, to = 20) cnt: Int) =
        getFormatted(R.string.title_count_formatter, cnt.toString())

    /**
     * Returns a [String] representation of a percentage value between
     * zero and one - hundred
     *
     * NOTE: This method does no sanitization on the float argument in terms of its actual value.
     * Clients are expected to round their values properly to fit this methods policies.
     */
    @SuppressLint("Range")
    fun getPercentageString(@FloatRange(from = 0.0, to = 1.0) value: Float): String {
        var strPercent = ""

        val wholeNum = (value * 100).toInt()

        // Isolation strings only. E.g. Those whose string values are one word only
        if (wholeNum <= 19 || (wholeNum % 10 == 0 && wholeNum != 100))
            strPercent = getFormatted(
                R.string.isolated_pebble_value,
                getIsolatedStringRepresentation(wholeNum)!!
            )!!
        else if (wholeNum <= 100 && wholeNum % 10 != 0) {
            val compound = getCompoundStringRepresentation(wholeNum)
            strPercent = getFormatted(
                R.string.compound_pebble_value, compound.first, compound.second
            )!!
        }

        return strPercent
    }

    /**
     * Resolves a [String] from its Reference id
     */
    fun get(@StringRes id: Int): String? = res?.get()?.getString(id)

    /**
     * Resolves a [String] that has been formatted according to the arguments
     */
    fun getFormatted(@StringRes id: Int, vararg args: String) = res?.get()?.getString(id, args)

    /**
     * Resolves a [String] with the proper amount of format expressions
     * for formatting multiple days in a single string
     */
    private fun getFormattedDayStringFromCount(@Size(min = 2, max = 7) cnt: Int): String =
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
            else -> ""
        }

    /**
     * Returns a [String] representation of a number / percentage whose value is
     * represented by at least two words. E.g. 22 / 0.22 = "TwentyTwo"
     */
    fun getCompoundStringRepresentation(
        @IntRange(
            from = 21,
            to = 100
        ) value: Int
    ): Pair<String, String> {
        //Split the value into ones and tens and return the proper value from each category

        //Convert to whole number between 0 and 100
        val wholeNumber = (value * 100)

        var firstValue: String? = null
        var secondValue: String? = null

        if (wholeNumber in 21..29) {
            firstValue = get(R.string.twenty)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 20)
        } else if (wholeNumber in 31..39) {
            firstValue = get(R.string.thirty)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 30)
        } else if (wholeNumber in 41..49) {
            firstValue = get(R.string.fourty)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 40)
        } else if (wholeNumber in 51..59) {
            firstValue = get(R.string.fifteen)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 50)
        } else if (wholeNumber in 61..69) {
            firstValue = get(R.string.sixteen)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 60)
        } else if (wholeNumber in 71..79) {
            firstValue = get(R.string.seventeen)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 70)
        } else if (wholeNumber in 81..89) {
            firstValue = get(R.string.eighteen)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 80)
        } else if (wholeNumber in 91..99) {
            firstValue = get(R.string.nineteen)
            secondValue = getIsolatedStringRepresentation(wholeNumber - 90)
        } else if (wholeNumber == 100) {
            firstValue = get(R.string.one)
            secondValue = get(R.string.hundred)
        } else throw IllegalArgumentException("Value must have a Tens and Ones spot AND be less than 100, OR be 100")

        return Pair(firstValue!!, secondValue!!)
    }

    /**
     * Clears the [Context] Reference from this classes heap
     */
    fun clear() {
        res?.clear()
    }
}
