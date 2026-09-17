package com.clogu.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clogu.lemonade.ui.theme.DarkGreen
import com.clogu.lemonade.ui.theme.LemonYellow
import com.clogu.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

private data class LemonadeScreen(
    @StringRes val instruction: Int,
    @DrawableRes val image: Int,
    @StringRes val contentDescription: Int
)

@Composable
fun LemonadeApp(initialState: LemonadeState = LemonadeState()) {
    var state by remember(initialState) { mutableStateOf(initialState) }
    val screen = screenFor(state.step)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LemonadeHeader()

            LemonadeContent(
                screen = screen,
                onImageClick = { state = nextLemonadeState(state) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LemonadeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(LemonYellow)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = DarkGreen,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LemonadeContent(
    screen: LemonadeScreen,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageShape = RoundedCornerShape(16.dp)
    val imageColor = Color(red = 105, green = 205, blue = 216)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(screen.image),
            contentDescription = stringResource(screen.contentDescription),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
                .clip(imageShape)
                .background(
                    color = imageColor.copy(alpha = 0.22f),
                    shape = imageShape
                )
                .border(
                    width = 2.dp,
                    color = imageColor,
                    shape = imageShape
                )
                .clickable(
                    role = Role.Button,
                    onClick = onImageClick
                )
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(screen.instruction),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

private fun screenFor(step: LemonadeStep): LemonadeScreen = when (step) {
    LemonadeStep.SELECT -> LemonadeScreen(
        instruction = R.string.lemon_select,
        image = R.drawable.lemon_tree,
        contentDescription = R.string.lemon_tree_description
    )

    LemonadeStep.SQUEEZE -> LemonadeScreen(
        instruction = R.string.lemon_squeeze,
        image = R.drawable.lemon_squeeze,
        contentDescription = R.string.lemon_description
    )

    LemonadeStep.DRINK -> LemonadeScreen(
        instruction = R.string.lemon_drink,
        image = R.drawable.lemon_drink,
        contentDescription = R.string.lemonade_description
    )

    LemonadeStep.RESTART -> LemonadeScreen(
        instruction = R.string.lemon_restart,
        image = R.drawable.lemon_restart,
        contentDescription = R.string.empty_glass_description
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
