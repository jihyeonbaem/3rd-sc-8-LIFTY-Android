package page.lifty.gdsclifty.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
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
        // UI 상태가 Success될 때까지 splash화면을 유지
        // 이 조건은 onCreate될 때마다 실행되므로 빨라야 한다.
        splashScreen.setKeepOnScreenCondition {
            // 조건이 true일 때까지 splash를 유지한다.
            false
        }

        enableEdgeToEdge()

        setContent {
            GdscLiftyTheme {
                MainApp(
                    // viewModel을 사용해서 isLogin이 true면 HOME_GRAPH로
                    // isLogin이 false면 LOGIN_GRAPH로
                    startDestination = if (true) HOME_GRAPH_ROUTE_PATTERN else LOGIN_GRAPH_ROUTE_PATTERN
                )
            }
        }
    }

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