package com.ahmet.HistoryApp.service

import com.ahmet.HistoryApp.model.Post


interface Listener {
    fun onItemClick(product: Post)
}
