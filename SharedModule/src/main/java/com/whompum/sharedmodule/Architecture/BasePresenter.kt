package com.whompum.sharedmodule.Architecture

abstract class BasePresenter<T: View>: Presenter<T> {

    protected var view: T? = null

    override fun attach(t: T) {
        this.view = t
    }
}