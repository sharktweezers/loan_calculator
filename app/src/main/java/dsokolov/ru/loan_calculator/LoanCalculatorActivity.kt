package dsokolov.ru.loan_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import dsokolov.ru.loan_calculator.di.Di
import dsokolov.ru.loan_calculator.ui.LoanCalculatorScreen
import dsokolov.ru.loan_calculator.ui.theme.LoanCalculatorTheme
import javax.inject.Inject

class LoanCalculatorActivity : ComponentActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Di.getComponent().inject(this)
        enableEdgeToEdge()
        setContent {
            LoanCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                    ) {
                        LoanCalculatorScreen(loanCalculatorViewModel = viewModel(
                            factory = viewModelFactory
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoanCalculatorTheme {
        Greeting("Android")
    }
}