package com.example.kalkulator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalkulator.ui.theme.KalkulatorTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults

enum class ButtonRole {
    NUMBER,
    OPERATION,
    ACTION,
    EQUALS,
    OTHER
}
data class ButtonData(val title: String, val role: ButtonRole, val onClick: () -> Unit = {})

val keypadButtons = listOf(
    ButtonData("C", ButtonRole.ACTION) {},
    ButtonData("⌫", ButtonRole.ACTION) {},
    ButtonData("(", ButtonRole.OTHER) {},
    ButtonData(")", ButtonRole.OTHER) {},
    ButtonData("7", ButtonRole.NUMBER) {},
    ButtonData("8", ButtonRole.NUMBER) {},
    ButtonData("9", ButtonRole.NUMBER) {},
    ButtonData("÷", ButtonRole.OPERATION) {},
    ButtonData("4", ButtonRole.NUMBER) {},
    ButtonData("5", ButtonRole.NUMBER) {},
    ButtonData("6", ButtonRole.NUMBER) {},
    ButtonData("×", ButtonRole.OPERATION) {},
    ButtonData("1", ButtonRole.NUMBER) {},
    ButtonData("2", ButtonRole.NUMBER) {},
    ButtonData("3", ButtonRole.NUMBER) {},
    ButtonData("-", ButtonRole.OPERATION) {},
    ButtonData("0", ButtonRole.NUMBER) {},
    ButtonData(".", ButtonRole.NUMBER) {},
    ButtonData("+", ButtonRole.OPERATION) {},
    ButtonData("=", ButtonRole.EQUALS) {}
)

val horizontalButtons = listOf(
    ButtonData("sin", ButtonRole.OPERATION),
    ButtonData("cos", ButtonRole.OPERATION),
    ButtonData("tan", ButtonRole.OPERATION),
    ButtonData("π", ButtonRole.OPERATION),
    ButtonData("√", ButtonRole.OPERATION),
    ButtonData("x^y", ButtonRole.OPERATION),
    ButtonData("ln", ButtonRole.OPERATION),
    ButtonData("e", ButtonRole.OPERATION),
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var inputText by remember {mutableStateOf("")}

    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val handleButtonClick: (ButtonData) -> Unit = {button ->
        when (button.title) {
            "C" -> inputText = ""
            "⌫" -> if (inputText.isNotEmpty()) inputText = inputText.dropLast(1)
            "=" -> {}
            "sin", "cos", "tan", "ln", "√" -> inputText += "${button.title}("
            "x^y" -> inputText += "^"
            else -> inputText += button.title
        }
    }

    Box(modifier = modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            CalcInput(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = if (isLandscape) Modifier else Modifier.weight(1f)
            )

            if (isLandscape){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ){
                    Keypad(
                        buttons = horizontalButtons,
                        onButtonClick = handleButtonClick,
                        columns = 4,
                        modifier = Modifier.weight(1f)
                    )
                    Keypad(
                        buttons = keypadButtons,
                        onButtonClick = handleButtonClick,
                        columns = 4,
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                Keypad(
                    buttons = keypadButtons,
                    onButtonClick = handleButtonClick,
                    columns = 4,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Composable
fun CalcInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = true,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.End,
                fontSize = 20.sp
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 2.dp,
                    bottom = 2.dp
                )
        )
    } else {
        val scrollState = rememberScrollState()

        LaunchedEffect(value) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = true,
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.End,
                fontSize = 28.sp
            ),
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
                .verticalScroll(scrollState)
        )
    }
}

@Composable
fun Keypad(
    buttons: List<ButtonData>,
    onButtonClick: (ButtonData) -> Unit,
    columns: Int = 4,
    modifier: Modifier = Modifier) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val buttonAspectRatio = if (isLandscape) 1.8f else 1f

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(if (isLandscape) 4.dp else 8.dp),
        verticalArrangement = Arrangement.spacedBy(if (isLandscape) 4.dp else 8.dp),
        contentPadding = if (isLandscape) PaddingValues(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 0.dp) else PaddingValues(8.dp)
    ) {
        items(buttons) { button ->
            val (containerColor, contentColor) = when (button.role) {
                ButtonRole.EQUALS -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
                ButtonRole.OPERATION -> MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
                ButtonRole.ACTION -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
                ButtonRole.NUMBER -> MaterialTheme.colorScheme.surfaceContainerHigh to MaterialTheme.colorScheme.onSurface
                ButtonRole.OTHER -> MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurfaceVariant
            }

            Button(
                onClick = {onButtonClick(button)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor,
                    contentColor = contentColor
                ),
                contentPadding = if (isLandscape) PaddingValues(0.dp) else PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                modifier = Modifier
                    .aspectRatio(buttonAspectRatio)
                    .fillMaxSize()
            ) {
                Text(
                    text = button.title,
                    fontSize = if (isLandscape) 17.sp else 20.sp
                )
            }
        }
    }
}
