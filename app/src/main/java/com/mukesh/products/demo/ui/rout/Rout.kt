package com.mukesh.products.demo.ui.rout

/**
Created by Mukesh
 **/

sealed class Rout (val rout: String){
    companion object{
        //paths
        private const val PRODUCT_LIST_PATH = "list"
        private const val PRODUCT_DETAILS_PATH = "details"
        private const val FAVORITE_PATH = "favorite"
    }

    //Routs
    object PRODUCT_LIST : Rout(rout = PRODUCT_LIST_PATH)
    object FAVORITE: Rout(rout = FAVORITE_PATH)
    object PRODUCT_DETAILS: Rout(rout = "$PRODUCT_DETAILS_PATH/{product_id}"){
        fun buildUrl(productId: String): String{
            return "$PRODUCT_DETAILS_PATH/$productId"
        }
    }
}