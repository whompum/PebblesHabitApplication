package com.whompum.app.Authentication

class AuthResponse(var username: String, var credential: Credential, var authCode: AuthCode) {

    sealed class AuthCode {
        class VALID(msg: String) : AuthCode()
        class INVALID_CREDENTIAL(msg: String) : AuthCode()
    }
}