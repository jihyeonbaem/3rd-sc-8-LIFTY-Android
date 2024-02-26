package page.lifty.gdsclifty.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import page.lifty.gdsclifty.app.MainActivityContract.MainActivityUiState
import page.lifty.gdsclifty.app.ui.MainApp
import page.lifty.gdsclifty.core.designsystem.theme.GdscLiftyTheme
import page.lifty.gdsclifty.feature.home.navigation.HOME_GRAPH_ROUTE_PATTERN
import page.lifty.gdsclifty.feature.login.navigation.LOGIN_GRAPH_ROUTE_PATTERN
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        handleIntent(intent)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        // uiState 업데이트
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainActivityViewModel.uiState
                    .onEach { uiState = it }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainActivityUiState.Loading -> true
                is MainActivityUiState.Success -> false
            }
        }

        enableEdgeToEdge()



        val startDestination = when (uiState) {
            is MainActivityUiState.Success -> {
                if ((uiState as MainActivityUiState.Success).userData.isLogin) {
                    HOME_GRAPH_ROUTE_PATTERN
                } else {
                    LOGIN_GRAPH_ROUTE_PATTERN
                }
            }
            MainActivityUiState.Loading -> LOGIN_GRAPH_ROUTE_PATTERN
        }

        setContent {
            GdscLiftyTheme {
                MainApp(
                    startDestination = startDestination
                )
            }
        }
    }

//    @Composable
//    fun plz() {
//        var selectedImageUri by remember {
//            mutableStateOf<Uri?>(null)
//        }
//        var selectedImageUris by remember {
//            mutableStateOf<List<Uri>>(emptyList())
//        }
//        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.PickVisualMedia(),
//            onResult = { uri -> selectedImageUri = uri }
//        )
//        val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.PickMultipleVisualMedia(),
//            onResult = { uris -> selectedImageUris = uris }
//        )
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            item {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceAround
//                ) {
//                    Button(onClick = {
//                        singlePhotoPickerLauncher.launch(
//                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                        )
//                    }) {
//                        Text(text = "Pick one photo")
//                    }
//                    Button(onClick = {
//                        multiplePhotoPickerLauncher.launch(
//                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                        )
//                    }) {
//                        Text(text = "Pick multiple photo")
//                    }
//                }
//            }
//
//            item {
//                AsyncImage(
//                    model = selectedImageUri,
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxWidth(),
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//            items(selectedImageUris) { uri ->
//                AsyncImage(
//                    model = uri,
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxWidth(),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(
        intent: Intent?,
    ) {
        Timber.tag("JHB").d("NewIntent")
        val accessToken = intent?.data?.getQueryParameter("accessToken")
        val refreshToken = intent?.data?.getQueryParameter("refreshToken")
        accessToken?.let { if (it.isNotEmpty()) mainActivityViewModel.saveAccessToken(it) }
        refreshToken?.let { if (it.isNotEmpty()) mainActivityViewModel.saveRefreshToken(it) }
        Timber.tag("JHB").d("accessToken: $accessToken")
        Timber.tag("JHB").d("refreshToken: $refreshToken")
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}