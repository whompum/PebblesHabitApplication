package com.whompum.uitoolbox

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.whompum.models.Day
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.runner.RunWith
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25])
class UiTextProviderTest {

    lateinit var sut: UiTextProvider

    @Before
    fun setup() {
        sut = UiTextProvider(
            getApplicationContext<Context>().resources
        )
    }

    @Test
    fun clear_clearsPreviousReference() {
        sut.clear()
        assertNull(sut.res!!.get())
    }

    @Test
    fun get_ReturnsProperString() {
        assertEquals("Mon", sut.get(R.string.monday))
    }

    @Test
    fun getDay_Monday_ReturnsProperly() {
        Assert.assertEquals("Mon", sut.getDay(Day.Monday))
    }

    @Test
    fun getDay_Tuesday_ReturnsProperly() {
        Assert.assertEquals("Tue", sut.getDay(Day.Tuesday))
    }

    @Test
    fun getDay_Wednesday_ReturnsProperly() {
        Assert.assertEquals("Wed", sut.getDay(Day.Wendesday))
    }

    @Test
    fun getDay_Thursday_ReturnsProperly() {
        Assert.assertEquals("Thurs", sut.getDay(Day.Thursday))
    }

    @Test
    fun getDay_Friday_ReturnsProperly() {
        Assert.assertEquals("Fri", sut.getDay(Day.Friday))
    }

    @Test
    fun getDay_Saturday_ReturnsProperly() {
        Assert.assertEquals("Sat", sut.getDay(Day.Saturday))
    }

    @Test
    fun getDay_Sunday_ReturnsProperly() {
        assertEquals("Sun", sut.getDay(Day.Sunday))
    }

    @Test
    fun getDay_AnyDay_NotNull() {
        assertNotNull(sut.getDay(Day.Monday))
    }

    @Test
    fun getFormatStringFromDayCount_ReturnsProperStringForCount() {
        assertEquals(sut.get(R.string.two_day_formatter), sut.getFormatStringFromDayCount(2))
        assertEquals(sut.get(R.string.three_day_formatter), sut.getFormatStringFromDayCount(3))
        assertEquals(sut.get(R.string.four_day_formatter), sut.getFormatStringFromDayCount(4))
        assertEquals(sut.get(R.string.five_day_formatter), sut.getFormatStringFromDayCount(5))
        assertEquals(sut.get(R.string.six_day_formatter), sut.getFormatStringFromDayCount(6))
        assertEquals(sut.get(R.string.seven_day_formatter), sut.getFormatStringFromDayCount(7))

        assertEquals("", sut.getFormatStringFromDayCount(-1))
        assertEquals("", sut.getFormatStringFromDayCount(1))
        assertEquals("", sut.getFormatStringFromDayCount(8))
    }

    @Test
    fun getDays_FormatsProperly() {
        assertEquals("Mon", sut.getDays(Day.Monday))
        assertEquals("Mon . Tue", sut.getDays(Day.Monday, Day.Tuesday))
        assertEquals("Mon . Tue . Wed", sut.getDays(Day.Monday, Day.Tuesday, Day.Wendesday))
        assertEquals("Mon . Tue . Wed . Thurs", sut.getDays(Day.Monday, Day.Tuesday, Day.Wendesday, Day.Thursday))
        assertEquals("Mon . Tue . Wed . Thurs . Fri", sut.getDays(Day.Monday, Day.Tuesday, Day.Wendesday, Day.Thursday, Day.Friday))
        assertEquals("Mon . Tue . Wed . Thurs . Fri . Sat", sut.getDays(Day.Monday, Day.Tuesday, Day.Wendesday, Day.Thursday, Day.Friday, Day.Saturday))
        assertEquals("Mon . Tue . Wed . Thurs . Fri . Sat . Sun", sut.getDays(Day.Monday, Day.Tuesday, Day.Wendesday, Day.Thursday, Day.Friday, Day.Saturday, Day.Sunday))

        assertEquals("", sut.getDays(Day.Monday, Day.Tuesday, Day.Wendesday, Day.Thursday, Day.Friday, Day.Saturday, Day.Sunday, Day.Saturday))
        assertEquals("", sut.getDays())
        //assertEquals(null, null)
    }

    @Test
    fun getDaySymbol_ReturnsFirstLetterOfDay() {
        assertEquals("M", sut.getDaySymbol(Day.Monday))
    }

    @Test
    fun getCounterStringRep() {
        assertEquals("(0 / 20)", sut.getCounterStringRep(0))
        assertEquals("(5 / 20)", sut.getCounterStringRep(5))
        assertEquals("(10 / 20)", sut.getCounterStringRep(10))
        assertEquals("(15 / 20)", sut.getCounterStringRep(15))
        assertEquals("(20 / 20)", sut.getCounterStringRep(20))
    }

    @Test
    fun getIsolatedStringRepresentation_ReturnsStringRepForAmount() {
        assertEquals("Zero", sut.getIsolatedStringRepresentation(0))
        assertEquals("Five", sut.getIsolatedStringRepresentation(5))
        assertEquals("Ten", sut.getIsolatedStringRepresentation(10))
        assertEquals("Fifteen", sut.getIsolatedStringRepresentation(15))
        assertEquals("Twenty", sut.getIsolatedStringRepresentation(20))
        assertEquals("Thirty", sut.getIsolatedStringRepresentation(30))
        assertEquals("Forty", sut.getIsolatedStringRepresentation(40))
        assertEquals("Fifty", sut.getIsolatedStringRepresentation(50))
        assertEquals("Sixty", sut.getIsolatedStringRepresentation(60))
        assertEquals("Seventy", sut.getIsolatedStringRepresentation(70))
        assertEquals("Eighty", sut.getIsolatedStringRepresentation(80))
        assertEquals("Ninety", sut.getIsolatedStringRepresentation(90))

        assertEquals("", sut.getIsolatedStringRepresentation(-1))
        assertEquals("", sut.getIsolatedStringRepresentation(21))
        assertEquals("", sut.getIsolatedStringRepresentation(100))
    }

    @Test
    fun getCompoundStringRepresentation_ReturnsStringRepForAmount() {
        val twentyOnePair = sut.getCompoundStringRepresentation(21)
        val fiftyNinePair = sut.getCompoundStringRepresentation(59)
        val oneHundredPair = sut.getCompoundStringRepresentation(100)
        val badPair = sut.getCompoundStringRepresentation(101)

        assertEquals("Twenty", twentyOnePair.first)
        assertEquals("One", twentyOnePair.second)
        assertEquals("Fifty", fiftyNinePair.first)
        assertEquals("Nine", fiftyNinePair.second)
        assertEquals("One", oneHundredPair.first)
        assertEquals("Hundred", oneHundredPair.second)

        assertEquals("", badPair.first)
        assertEquals("", badPair.second)
    }

    @Test
    fun getPercentageString_ReturnsStringRepForAmount() {
        assertEquals("Zero Percent", sut.getPercentageString(0.0F))
        assertEquals("Three Percent", sut.getPercentageString(0.03F))
        assertEquals("Ten Percent", sut.getPercentageString(0.10F))
        assertEquals("Fifteen Percent", sut.getPercentageString(0.15F))
        assertEquals("Seventeen Percent", sut.getPercentageString(0.17F))
        assertEquals("Nineteen Percent", sut.getPercentageString(0.19F))
        assertEquals("Twenty-One Percent", sut.getPercentageString(0.21F))
        assertEquals("Thirty Percent", sut.getPercentageString(0.30F))
        assertEquals("Thirty-Seven Percent", sut.getPercentageString(0.37F))
        assertEquals("Ninety Percent", sut.getPercentageString(0.90F))
        assertEquals("Ninety-Nine Percent", sut.getPercentageString(0.99F))
        assertEquals("One-Hundred Percent", sut.getPercentageString(1F))
    }

}