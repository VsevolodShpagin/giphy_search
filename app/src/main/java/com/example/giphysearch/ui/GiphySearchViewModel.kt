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

    fun updateInputText(inputText: String) {
        this.inputText = inputText
        getGifs(searchText = inputText)
    }

    private fun getGifs(searchText: String) {
        viewModelScope.launch {
            delay(2000L)
            if (searchText == inputText) {
                uiState = try {
                    GiphySearchUiState.Success(
                        gifRepository.getGifs(searchText = searchText).gifs
                    )
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
