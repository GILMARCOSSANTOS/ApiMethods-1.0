package com.example.apimethods10.service

class InitialConfig {
    private var RUN_ENV: String = "sandbox"

    enum class EnviromentConfig(val env: String) {
        DEV("dev"), HML("hml"), SANDBOX("sandbox"), PRODUCTION("production")
    }

    class Enviroment() {
        var urlbase: String = ""
        var apiKey: String = ""
    }

    fun getEnv(): Enviroment {
        var env = Enviroment()
        when (RUN_ENV) {
            EnviromentConfig.DEV.env -> {
                env.apiKey = ""
                env.urlbase = "https://jsonplaceholder.typicode.com/"
            }
            EnviromentConfig.HML.env -> {
                env.apiKey = ""
                env.urlbase = "https://jsonplaceholder.typicode.com/"
            }
            EnviromentConfig.SANDBOX.env -> {
                env.apiKey = ""
                env.urlbase = "https://jsonplaceholder.typicode.com/"
            }
            else -> {
                env.apiKey = ""
                env.urlbase = ""
            }
        }
        return env
    }
}