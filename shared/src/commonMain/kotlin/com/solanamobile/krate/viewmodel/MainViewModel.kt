package com.solanamobile.krate.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import cafe.adriel.voyager.core.model.StateScreenModel
import com.moriatsushi.koject.Provides
import com.solanamobile.krate.coroutines.Dispatcher
import com.solanamobile.krate.coroutines.Dispatchers
import com.solanamobile.krate.graphics.toImageBitmap
import com.solanamobile.krate.repository.GetImgRepository
import com.solanamobile.krate.repository.PlatformRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

sealed class ViewState() {

    data object Default : ViewState()

    data object Loading : ViewState()

    data class Generated(
        val bitmap: ImageBitmap? = null
    ): ViewState()
}

@Provides
class MainViewModel(
    private val repository: PlatformRepository,
    private val getImgRepository: GetImgRepository,
    @Dispatcher(Dispatchers.Main)
    private val dispatcher: CoroutineDispatcher
): StateScreenModel<ViewState>(ViewState.Default) {
    private val job = SupervisorJob()

    private val coroutineScope: CoroutineScope
        get() = CoroutineScope(job + dispatcher)

//    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Default)
//
//    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    @OptIn(ExperimentalEncodingApi::class)
    fun generateImageFromPrompt(prompt: String) {
        coroutineScope.launch {
            mutableState.update {
                ViewState.Loading
            }

            val imgString = getImgRepository.generateImage(prompt)

            val decodedbytes = Base64.decode(imgString)

            mutableState.update {
                ViewState.Generated(
                    bitmap = decodedbytes.toImageBitmap()
                )
//                ViewState.Generated()
            }
        }
    }
}