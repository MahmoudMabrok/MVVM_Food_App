package com.af.foodapp.ui.features.home_screen

import androidx.lifecycle.ViewModel
import com.af.foodapp.data.IHomeRepository

class HomeViewModel(
    private val homeRepository: IHomeRepository
) : ViewModel() {
    fun getRandomMeal() = homeRepository.getRandomMeal()

    fun getPopularItems() = homeRepository.getPopularItems()

    fun getCategories() = homeRepository.getCategories()

}