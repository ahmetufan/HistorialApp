package com.ahmet.HistoryApp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmet.HistoryApp.model.Post

class HomeViewModel:ViewModel() {

    val basket = MutableLiveData<List<Post>>()


    fun addToBasket(product: Post) {
        if (basket.value != null) {
            val arrayList=ArrayList(basket.value)
            if (arrayList.contains(product)) {
                val indexOfFirst=arrayList.indexOfFirst{it== product}
                val relatedProduct=arrayList.get(indexOfFirst)
                relatedProduct.count+=1
                basket.value=arrayList

            } else {
                product.count+=1
                arrayList.add(product)
                basket.value=arrayList
            }

        } else {
            val arrayList= arrayListOf(product)
            product.count+=1
            basket.value=arrayList
        }
        basket.value.let {
        }
    }

    fun deleteProductBasket(product: Post) {
        if (basket.value != null) {

            val arrayList=ArrayList(basket.value)
            arrayList.remove(product)
            basket.value=arrayList
           // refreshTotalValue(arrayList)
        }
    }



}