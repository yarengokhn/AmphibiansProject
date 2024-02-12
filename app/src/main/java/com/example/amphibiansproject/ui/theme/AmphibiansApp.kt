package com.example.amphibiansproject.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansproject.R
import com.example.amphibiansproject.model.mockData
import com.example.amphibiansproject.ui.theme.screens.AmphibiansUiState
import com.example.amphibiansproject.ui.theme.screens.AmphibiansViewModel
import com.example.amphibiansproject.ui.theme.screens.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp(){
     val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AmphibiansTopAppBar(scrollBehavior = scrollBehavior) }
    ) {

        Surface (
            modifier = Modifier.fillMaxSize()
        ){
            val amphibiansViewModel:AmphibiansViewModel= viewModel(factory = AmphibiansViewModel.Factory)

            HomeScreen(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = amphibiansViewModel ::getAmphibiansInfos

            )

        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier=Modifier) {
   TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = { 
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Start
            )
       },

    )

}