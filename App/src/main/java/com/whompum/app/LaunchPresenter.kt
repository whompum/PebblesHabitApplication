package com.whompum.app

import com.whompum.app.BuildConfig.ADMIN_PASSWORD
import com.whompum.app.BuildConfig.ADMIN_USERNAME
import com.whompum.app.BuildConfig.USE_ADMIN_ACCOUNT
import timber.log.Timber

class LaunchPresenter : LaunchContract.LaunchPresenter() {

    override fun validateUsernameAndPassword(username: String, password: String) {

        Timber.i("Username: $username, Password: $password entered. USE_ADMIN_ACCOUNT: $USE_ADMIN_ACCOUNT")

        if (USE_ADMIN_ACCOUNT)
            view?.onCredentialsValidated(
                username,
                password,
                (username == ADMIN_USERNAME && password == ADMIN_PASSWORD)
            )
    }

}
