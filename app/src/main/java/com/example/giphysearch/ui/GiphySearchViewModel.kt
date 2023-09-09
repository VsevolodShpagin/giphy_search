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
import com.example.giphysearch.data.repository.GifRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GiphySearchViewModel(private val gifRepository: GifRepository) : ViewModel() {

    var giphySearchUiState: GiphySearchUiState by mutableStateOf(GiphySearchUiState.Error("PH"))
    var inputText by mutableStateOf("")
        private set

    init {

    }

    fun updateInputText(inputText: String) {
        this.inputText = inputText
        //delay ?
        getGifs(searchText = inputText)
    }

    private fun getGifs(searchText: String) {
        viewModelScope.launch {
            giphySearchUiState = try {
                GiphySearchUiState.Success(
                    gifRepository.getGifs(searchText = searchText)
                )
            } catch (e: IOException) {
                GiphySearchUiState.Error(errorText = e.message ?: "error")
            } catch (e: HttpException) {
                GiphySearchUiState.Error(errorText = e.response()?.errorBody().toString())
                //GiphySearchUiState.Error
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