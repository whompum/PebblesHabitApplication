package com.whompum.app.Authentication

interface AuthContract {
    interface AuthClient {
        fun onCredentialsAuthenticated(authResponse: AuthResponse)
    }
    interface AuthService {
        fun setClient(client: AuthClient)
        fun validateCredentials(credentials: Pair<String, Credential>)
    }
}