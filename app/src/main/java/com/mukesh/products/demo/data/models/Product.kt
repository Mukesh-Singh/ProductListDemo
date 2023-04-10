package com.mukesh.products.demo.data.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Product(
    @SerializedName("category")
    @Expose
    var category: String?,
    @SerializedName("description")
    @Expose
    var description: String?,
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("image")
    @Expose
    var image: String?,
    @SerializedName("price")
    @Expose
    var price: Double?,
    @SerializedName("rating")
    @Expose
    var rating: Rating?,
    @SerializedName("title")
    @Expose
    var title: String?
) {
    data class Rating(
        @SerializedName("count")
        @Expose
        var count: Int?,
        @SerializedName("rate")
        @Expose
        var rate: Double?
    )

    companion object {
        fun getSampleItem(): Product {
            return Product(
                id = 20,
                title = "DANVOUY Womens T Shirt Casual Cotton Short",
                price = 12.99,
                description = "95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.",
                category = "women's clothing",
                image = "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg",
                rating = Rating(
                    rate = 3.6,
                    count = 145
                )
            )
        }
    }
}