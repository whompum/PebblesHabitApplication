package com.whompum.sharedmodule.Architecture

interface Presenter<T: View> {
    fun attach(t: T)
}