package com.whompum.models

/**
 * Custom constants for dealing with Days of the Week
 */
sealed class Day {
    /**
     * Constant Representing [Monday] Day of the Week
     */
    object Monday : Day()

    /**
     * Constant Representing [Tuesday] Day of the Week
     */
    object Tuesday : Day()

    /**
     * Constant Representing [Wendesday] Day of the Week
     */
    object Wendesday : Day()

    /**
     * Constant Representing [Thursday] Day of the Week
     */
    object Thursday : Day()

    /**
     * Constant Representing [Friday] Day of the Week
     */
    object Friday : Day()

    /**
     * Constant Representing [Saturday] Day of the Week
     */
    object Saturday : Day()

    /**
     * Constant Representing [Sunday] Day of the Week
     */
    object Sunday : Day()
}
