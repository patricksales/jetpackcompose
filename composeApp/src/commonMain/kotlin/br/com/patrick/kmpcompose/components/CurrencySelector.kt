package br.com.patrick.kmpcompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kmpcompose.composeapp.generated.resources.Res
import kmpcompose.composeapp.generated.resources.ic_stat
import org.jetbrains.compose.resources.painterResource

@Composable
fun CurrencySelector(
    modifier: Modifier = Modifier,
    currencies: List<String>,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .clickable {
                expanded = true
            }
            .padding(16.dp)
    ) {
        Text(
            text = selectedCurrency,
            fontWeight = FontWeight.Bold
        )

        Icon(
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp),
            painter = painterResource(Res.drawable.ic_stat),
        )
    }


    Box {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            currencies.forEach { currency ->
                DropdownMenuItem(
                    text = { Text(currency) },
                    onClick = {
                        onCurrencySelected(currency)
                        expanded = false
                    }
                )
            }
        }
    }

}