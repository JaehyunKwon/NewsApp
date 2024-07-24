package com.test.mobile.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mobile.network.repository.NewsRepository
import com.test.mobile.network.request.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
): ViewModel() {
    private val _newsListLiveData : MutableLiveData<List<Articles>> = MutableLiveData()
    val newsListLiveData : LiveData<List<Articles>>
        get() = _newsListLiveData

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    fun getNews() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                try {
                    val result = newsRepository.getNews()
                    if (result.isSuccessful) {
                        result.body()?.let { response ->
                            isLoading.value = false
                            isError.value = false
                            _newsListLiveData.value = response.articles
                        }
                    } else {
                        isError.value = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    isError.value = true
                } finally {
                    isLoading.value = false
                }
            }
        }
    }

    /*// DB 즐겨찾기 user 추가
    fun addFavoriteUser(user: Articles) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoriteUserRepository.addFavoriteUser(user)
            }
        }
    }

    // DB 즐겨찾기 user 조회
    fun getFavoriteUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val users = favoriteUserRepository.getFavoriteUsers()
                val sortedUsers = users.sortedBy { it.login.lowercase() }
                _favoriteUsers.postValue(sortedUsers)
            }
        }
    }

    // DB 즐겨찾기 user 제거
    fun deleteFavoriteUsers(user: Articles) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoriteUserRepository.removeFavoriteUser(user.id)
            }
        }
    }*/
}