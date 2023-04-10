package com.mukesh.products.demo.data.repository.datasource.remote.exceptions

import java.io.IOException

/**
Created by Mukesh
 **/

class APIConnectivityException(override val message: String?, override val cause: Throwable?) : IOException(message, cause) {
    companion object{
        const val CODE= 800
    }

}