package com.whompum.uitoolbox

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.annotation.Size
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import com.whompum.commonui.R
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
     * TODO Consider moving from this class?
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

        // Isolation strings only. E.g. Those whose string values are one word only
        if (value in 0.0..0.19)
            strPercent = getFormatted(
                R.string.isolated_pebble_value,
                getIsolatedStringRepresentation(value)!!
            )!!
        else if (value in 0.2..1.0)
            strPercent = getFormatted(
                R.string.compound_pebble_value,
                getCompoundStringRepresentation(value)
            )!!

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
    fun getIsolatedStringRepresentation(@FloatRange(from = 0.0, to = 0.19) value: Float) =
        when (value) {
            0F -> get(R.string.zero)
            0.01F -> get(R.string.one)
            0.02F -> get(R.string.two)
            0.03F -> get(R.string.three)
            0.04F -> get(R.string.four)
            0.05F -> get(R.string.five)
            0.06F -> get(R.string.six)
            0.07F -> get(R.string.seven)
            0.08F -> get(R.string.eight)
            0.09F -> get(R.string.nine)
            0.10F -> get(R.string.ten)
            0.11F -> get(R.string.eleven)
            0.12F -> get(R.string.twelve)
            0.13F -> get(R.string.thirteen)
            0.14F -> get(R.string.fourteen)
            0.15F -> get(R.string.fifteen)
            0.16F -> get(R.string.sixteen)
            0.17F -> get(R.string.seventeen)
            0.18F -> get(R.string.eighteen)
            0.19F -> get(R.string.nineteen)
            else -> ""
        }

    /**
     * Returns a [String] representation of a number / percentage whose value is
     * represented by at least two words. E.g. 22 / 0.22 = "TwentyTwo"
     */
    fun getCompoundStringRepresentation(@FloatRange(from = 0.2, to = 1.0) value: Float): String {
        //Split the value into ones and tens and return the proper value from each category
        return ""
    }

    /**
     * Clears the [Context] Reference from this classes heap
     */
    fun clear() {
        res?.clear()
    }
}
