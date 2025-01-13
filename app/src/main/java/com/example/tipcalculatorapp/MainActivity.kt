package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf("") }
    val bill = billAmount.toDoubleOrNull() ?: 0.0
    val tipPercent = tipPercentage.toDoubleOrNull() ?: 0.0
    val tipAmount = bill * (tipPercent / 100)
    val totalAmount = bill + tipAmount

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Input for bill amount
        TextField(
            value = billAmount,
            onValueChange = { billAmount = it },
            label = { Text(text = stringResource(id = R.string.bill_amount)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Input for tip percentage
        TextField(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            label = { Text(text = stringResource(id = R.string.tip_percentage)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Display tip amount
        Text(
            text = stringResource(
                id = R.string.tip_amount,
                NumberFormat.getCurrencyInstance().format(tipAmount)
            ),
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        // Display total amount
        Text(
            text = stringResource(
                id = R.string.total_amount,
                NumberFormat.getCurrencyInstance().format(totalAmount)
            ),
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipTimePreview() {
    TipCalculatorAppTheme {
        TipTimeScreen()
    }
}
