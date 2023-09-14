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
import com.example.giphysearch.R
import com.example.giphysearch.repository.GifRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GiphySearchViewModel(private val gifRepository: GifRepository) : ViewModel() {

    var uiState: GiphySearchUiState by mutableStateOf(GiphySearchUiState.Blank(R.string.blank_screen_text))
        private set
    var inputText by mutableStateOf("")
        private set

    private var offset: Int = 0

    fun updateInputText(inputText: String) {
        this.inputText = inputText
        resetScreen()
        getGifs(inputTextCalledWith = inputText)
    }

    fun onListEndReached() {
        getGifs(inputTextCalledWith = inputText)
    }

    private fun getGifs(inputTextCalledWith: String) {
        viewModelScope.launch {
            delay(1000L)
            if (inputText.isNotBlank() && inputTextCalledWith == inputText) {
                uiState = try {
                    val response = gifRepository.getGifs(searchText = inputText, offset = offset)
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

    private fun resetScreen() {
        uiState = if (inputText.isNotBlank()) {
            GiphySearchUiState.Blank(R.string.loading_text)
        } else {
            GiphySearchUiState.Blank(R.string.blank_screen_text)
        }
        offset = 0
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
