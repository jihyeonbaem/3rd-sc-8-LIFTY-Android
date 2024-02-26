package page.lifty.gdsclifty.feature.login

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import page.lifty.gdsclifty.R
import page.lifty.gdsclifty.core.designsystem.component.LiftyButton
import page.lifty.gdsclifty.core.designsystem.component.LiftySpacer
import page.lifty.gdsclifty.core.designsystem.component.LiftyText

@Composable
internal fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
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
    onGoogleLoginClick: () -> Unit,
    onHomeClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LiftyText(text = "LIFTY", style = TextStyle(fontSize = 100.sp, fontWeight = FontWeight.W700))
        LiftySpacer(size = 80.dp)
        GoogleLoginButton(
            onGoogleLoginClick = onGoogleLoginClick
        )
//        Button(
//            onClick = onHomeClick
//        ) {
//            Text(text = "Go Home")
//        }
    }
}

@Composable
fun GoogleLoginButton(
    modifier: Modifier = Modifier.padding(start = 20.dp, end = 20.dp),
    onGoogleLoginClick: () -> Unit,
) {
    LiftyButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onGoogleLoginClick,
        containerColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            LiftySpacer(size = 20.dp)
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.google_icon),
                contentDescription = "google_icon"
            )
            LiftySpacer(size = 20.dp)
            LiftyText(
                text = "Sign in with Google",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    Box(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 40.dp)
    ) {
        GoogleLoginButton(
            onGoogleLoginClick = {}
        )
    }

}