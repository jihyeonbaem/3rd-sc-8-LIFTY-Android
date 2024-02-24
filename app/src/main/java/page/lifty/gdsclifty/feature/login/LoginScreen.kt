package page.lifty.gdsclifty.feature.login

import android.app.Activity
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun LoginRoute(
    onHomeClick: () -> Unit
) {
    val context = LocalContext.current
    val customTabsIntent = CustomTabsIntent.Builder()
        .build()
    LoginScreen(
        onGoogleLoginClick = {
            customTabsIntent.launchUrl(context, Uri.parse(GOOGLE_LOGIN_URL))
        },
        onHomeClick = onHomeClick
    )
}

const val GOOGLE_LOGIN_URL = "https://dev.api.lifty.page/oauth2/authorization/google"


@Composable
internal fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onGoogleLoginClick: () -> Unit,
    onHomeClick: () -> Unit,
) {
    val readAccessToken by loginViewModel.getAccessToken.collectAsState()
    val readRefreshToken by loginViewModel.getRefreshToken.collectAsState()

    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        Button(
            onClick = onGoogleLoginClick
        ) {
            Text(text = "Google Login")
        }
        Text(text = "readAccessToken: $readAccessToken")
        Text(text = "readRefreshToken: $readRefreshToken")
        Button(
            onClick = onHomeClick
        ) {
            Text(text = "Go Home")
        }
    }
}