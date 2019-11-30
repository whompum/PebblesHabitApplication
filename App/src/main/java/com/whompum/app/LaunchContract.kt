package com.whompum.app

import com.whompum.sharedmodule.Architecture.BasePresenter
import com.whompum.sharedmodule.Architecture.View

interface LaunchContract {
    interface LaunchView: View {
        fun onCredentialsValidated(username: String, password: String, isValid: Boolean)
    }
    abstract class LaunchPresenter: BasePresenter<LaunchView>() {
        abstract fun validateUsernameAndPassword(username: String, password: String)
    }
}