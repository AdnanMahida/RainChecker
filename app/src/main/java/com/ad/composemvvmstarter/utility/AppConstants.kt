package com.ad.composemvvmstarter.utility

object AppConstants {
    /**
     * Network constants
     * */
    const val CONNECTION_TIMEOUT: Long = 180
    const val READ_TIMEOUT: Long = 120
    const val WRITE_TIMEOUT: Long = 120

    const val AUTHENTICATION_ERROR_CODE = 401
    const val BAD_GATEWAY_ERROR_CODE = 502
    const val NETWORK_ERROR_CODE = 1001

    const val NETWORK_MESSAGE =
        "It seems your internet is not available, please check it and try again later."
    const val SESSION_EXPIRED = "Session Expired"
    const val TIMEOUT_MESSAGE = "Socket Timeout"
    const val SOMETHING_WENT_WRONG = "Something went wrong"

    /**
     * DB Nam
     * */
    const val DB_NAME = "DemoDB"
    const val DB_VERSION = 1

    /**
     * Local Table Names
     * */
    const val TABLE_DEMO = "Demo" // for demo

}