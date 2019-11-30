package com.whompum.app

import com.whompum.app.BuildConfig.ADMIN_PASSWORD
import com.whompum.app.BuildConfig.ADMIN_USERNAME
import com.whompum.app.BuildConfig.USE_ADMIN_ACCOUNT

class LaunchPresenter : LaunchContract.LaunchPresenter() {

    override fun validateUsernameAndPassword(username: String, password: String) {
        if (USE_ADMIN_ACCOUNT)
            view?.onCredentialsValidated(
                username,
                password,
                (username == ADMIN_USERNAME && password == ADMIN_PASSWORD)
            )
    }

}
