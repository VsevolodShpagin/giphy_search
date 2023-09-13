package com.example.giphysearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.giphysearch.GiphySearchApplication
import com.example.giphysearch.model.Gif
import com.example.giphysearch.repository.GifRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@OptIn(FlowPreview::class)
class GiphySearchViewModel(private val gifRepository: GifRepository) : ViewModel() {

//    private val _uiState = MutableStateFlow(GiphySearchUiState())
//    val uiState = _uiState.asStateFlow()

    private val _errorText = MutableStateFlow("")
    val errorText = _errorText.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    private val _gifs = MutableStateFlow<List<Gif>>(listOf())
    val gifs = inputText
        .debounce(1000L)
        .combine(_gifs) { inputText, gifs ->
            if (inputText.isBlank()) {
                listOf()
            } else {
                getGifs(inputText)
                gifs
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _gifs.value
        )

    private var offset: Int = 0

    fun onInputTextChange(inputText: String) {
        _inputText.value = inputText
        offset = 0
    }

    fun onListEndReached() {
        getGifs(_inputText.value)
        _gifs.update { gifs.value }
    }

    private fun getGifs(searchText: String) {
        viewModelScope.launch {
            try {
                val currentGifs = _gifs.value
                val response = gifRepository.getGifs(searchText = searchText, offset = offset)
                offset += response.pagination.count
                _gifs.update { currentGifs + response.gifs }
            } catch (e: IOException) {
                _errorText.value = e.message ?: ""
                //_uiState.update { state -> state.copy(errorText = e.message ?: "") }
            } catch (e: HttpException) {
                _errorText.value = e.response()?.errorBody().toString()
                //_uiState.update { state -> state.copy( errorText = e.response()?.errorBody().toString()) }
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
