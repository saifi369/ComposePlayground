package com.u4universe.composeplayground.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.u4universe.composeplayground.ui.theme.ComposePlaygroundTheme
import com.u4universe.composeplayground.xml.PRICE_CHEESE
import com.u4universe.composeplayground.xml.PRICE_DOUBLE_PETTY
import com.u4universe.composeplayground.xml.PRICE_SINGLE_PETTY

class BurgerComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(innerPadding)
                }
            }
        }
    }

}

@Composable
fun Content(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var isDoublePatty by remember { mutableStateOf(false) }
        var isCheese by remember { mutableStateOf(false) }

        val basePrice =
            if (isDoublePatty) PRICE_DOUBLE_PETTY else PRICE_SINGLE_PETTY
        val cheesePrice = if (isCheese) PRICE_CHEESE else 0
        val totalPrice = basePrice + cheesePrice

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Please select your order 😋",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.size(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = !isDoublePatty,
                    onClick = { isDoublePatty = false }
                )
                Text(text = "Single Patty ($PRICE_SINGLE_PETTY)")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = isDoublePatty,
                    onClick = { isDoublePatty = true }
                )
                Text(text = "Double Patty ($PRICE_DOUBLE_PETTY)")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isCheese,
                    onCheckedChange = { isCheese = it }
                )
                Text("Add Extra Cheese (+$PRICE_CHEESE)")
            }
            Spacer(Modifier.size(32.dp))

            Text(
                text = "Total: $totalPrice",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            Spacer(Modifier.size(32.dp))

            Button(onClick = {
                isDoublePatty = false
                isCheese = false
            }) {
                Text(text = "Reset Order")
            }
        }
    }
}

@Preview
@Composable
fun ContentPreview() {
    ComposePlaygroundTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Content(innerPadding)
        }
    }
}
