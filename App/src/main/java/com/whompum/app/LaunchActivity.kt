package com.whompum.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.whompum.app.Account.ValidationStrategies.notNullOrEmptyValidation
import com.whompum.app.Account.ValidationStrategies.passwordMeetsBasicRequirements
import com.whompum.main.MainActivity
import com.whompum.sharedmodule.Screen.LoginScreen
import com.whompum.uitoolbox.Utils.makeErrorSnackbar
import kotlinx.android.synthetic.main.screen_login.*
import javax.inject.Inject

//TODO Setup Signup flow
//TODO Setup for ADMIN build variant / ADMIN mode
//Setup error handling for text / api responses
class LaunchActivity : AppCompatActivity(), LaunchContract.LaunchView, LoginScreen {

    @Inject
    lateinit var presenter: LaunchContract.LaunchPresenter

    private val loginAction: (View?) -> Unit = {

        var usernameValidated = false
        var passwordValidated = false

        if (notNullOrEmptyValidation(password_edit_text.text)) {
            if (passwordMeetsBasicRequirements(password_edit_text.text!!)) {
                passwordValidated = true
            } else {
                displayErrorSnackbar(getString(R.string.invalid_password_error))
            }
        } else {
            displayErrorSnackbar(getString(R.string.empty_password_error))
        }

        if (notNullOrEmptyValidation(username_edit_text.text)) {
            usernameValidated = true
        } else {
            displayErrorSnackbar(getString(R.string.empty_username_error))
        }

        if (usernameValidated && passwordValidated) {
            presenter.validateUsernameAndPassword(
                username_edit_text.text!!.toString(),
                password_edit_text.text!!.toString()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Redirect to main theme after splash theme set in the manifest
        setTheme(R.style.AppThemeMain)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_login)

        presenter = LaunchPresenter()

        main_fab.animation = AnimationUtils.loadAnimation(this, R.anim.explode_animation)

        presenter.attach(this)
        //loginButton.setOnClickListener(loginAction)
        loginButton.setOnClickListener { openApp() }
    }

    override fun onCredentialsValidated(username: String, password: String, isValid: Boolean) {
        //If isValid is false, show proper error message!
        if (isValid) {
            //Use nav components L8R
            openApp()
        }
    }


    fun openApp() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    fun displayErrorSnackbar(msg: String) {
        makeErrorSnackbar(root_view, msg).show()
    }

}