package com.example.amphibiansproject.ui.theme.screens


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansproject.AmphibiansApplication
import com.example.amphibiansproject.data.AmphibiansRepository
import com.example.amphibiansproject.model.Amphibian
import com.example.amphibiansproject.model.getMockDatas
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import kotlin.random.Random

sealed interface AmphibiansUiState {
    class Success (val amphibians: List <Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}



class AmphibiansViewModel (
    private val amphibiansRepository: AmphibiansRepository
): ViewModel(){

    var amphibiansUiState:AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
    private set

    init {
       // dummyFunction()
        getAmphibiansInfos()
    }

    val retryAction: () -> Unit = {
       // dummyFunction()
        getAmphibiansInfos()
    }


    fun getAmphibiansInfos() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibiansRepository.getAmphibiansInfo())
            }
            catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }

        }

    }


    fun randomNumberGenerator(min:Int,max:Int):Int {
        var random by mutableStateOf(0)
        random = Random.nextInt(min,max+1)
        return random
    }



    fun dummyFunction() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            delay(5000)
            val randValue = randomNumberGenerator(0, 10)
            if(randValue % 2 == 0) {

                val mockData = getMockDatas()
                amphibiansUiState = AmphibiansUiState.Success(mockData)

            }
            else{
                amphibiansUiState = AmphibiansUiState.Error
            }


        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository=amphibiansRepository)


            }
        }
    }
}


