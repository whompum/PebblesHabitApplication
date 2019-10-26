package com.whompum.sharedmodule

/**
 * Interface implemented by [Screen]'s that can be navigated to
 */
interface Screen {
    /**
     * Screen Interface representing the screen to login at
     */
    interface LoginScreen: Screen

    /**
     * Screen Interface representing the Dashboard screen
     */
    interface DashboardScreen: Screen

    /**
     * Screen Interface representing the statistics screen
     */
    interface StatisticsScreen: Screen

    /**
     * Screen Interface representing the setting screen
     */
    interface SettingsScreen: Screen

    /**
     * Screen Interface representing the base-editing screen
     */
    interface HabitEditorScreen: Screen

    /**
     * Screen Interface representing the habit creation screen
     */
    interface HabitCreationScreen: Screen
}