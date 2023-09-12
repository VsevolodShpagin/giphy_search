package com.example.giphysearch.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.giphysearch.GiphySearchApplication
import com.example.giphysearch.repository.GifRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GiphySearchViewModel(private val gifRepository: GifRepository) : ViewModel() {

    var uiState: GiphySearchUiState by mutableStateOf(GiphySearchUiState.Blank)
        private set
    var inputText by mutableStateOf("")
        private set

    var offset: Int = 0
        private set
    //private var offset: Int = 0

    fun updateInputText(inputText: String) {
        this.inputText = inputText
        offset = 0
        getGifs(searchText = inputText)
    }

    fun onListEndReached() {
        getGifs(searchText = inputText)
    }

    private fun getGifs(searchText: String) {
        viewModelScope.launch {
            delay(2000L)
            if (searchText == inputText) {
                uiState = try {
                    val response = gifRepository.getGifs(searchText = searchText, offset = offset)
                    offset += response.pagination.count
                    val currentGifs = (uiState as? GiphySearchUiState.Success)?.gifs ?: listOf()
                    val gifs = currentGifs + response.gifs
                    GiphySearchUiState.Success(gifs)
                } catch (e: IOException) {
                    GiphySearchUiState.Error(errorText = e.message ?: "")
                } catch (e: HttpException) {
                    GiphySearchUiState.Error(errorText = e.response()?.errorBody().toString())
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GiphySearchApplication)
                val gifRepository = application.container.gifRepository
                GiphySearchViewModel(gifRepository = gifRepository)
            }
        }
    }

}
