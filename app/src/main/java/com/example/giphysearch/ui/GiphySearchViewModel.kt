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
import com.example.giphysearch.pagination.PaginatorDefaultImpl
import com.example.giphysearch.repository.GifRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GiphySearchViewModel(private val gifRepository: GifRepository) : ViewModel() {

    var uiState by mutableStateOf(GiphySearchUiState())
        private set
    var inputText by mutableStateOf("")
        private set

    private val limit = 50

    private val paginator = PaginatorDefaultImpl(
        initialOffset = uiState.offset,
        onRequest = { offset ->
            gifRepository.getGifs(searchText = inputText, limit = limit, offset = offset).gifs
        },
        getNextOffset = { items ->
            uiState.offset + items.size
        },
        onSuccess = { items, newOffset ->
            uiState = uiState.copy(
                gifs = uiState.gifs + items,
                offset = newOffset,
                endReached = items.isEmpty()
            )
        }
    )

    fun onInputTextChanged(inputText: String) {
        this.inputText = inputText
        paginator.reset()
        loadNextItems()
    }

    fun onListEndReached() {
        loadNextItems()
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            val searchText = inputText
            delay(1000L)
            if (searchText == inputText) {
                try {
                    paginator.loadNextItems()
                } catch (e: IOException) {
                    uiState = uiState.copy(errorText = e.message ?: "")
                } catch (e: HttpException) {
                    uiState = uiState.copy(errorText = e.response()?.errorBody().toString())
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
