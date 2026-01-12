package dsokolov.ru.loan_calculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorUiState
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorViewModel
import dsokolov.ru.loan_calculator.ui.theme.GRID_2

@Composable
fun LoanCalculatorScreen(loanCalculatorViewModel: LoanCalculatorViewModel) {
    Column(Modifier.padding(GRID_2.dp)) {
        val uiState by loanCalculatorViewModel.stateFlow.collectAsStateWithLifecycle()
        when (uiState) {
            is LoanCalculatorUiState.Loading -> Loading()
            is LoanCalculatorUiState.Error -> Error()
            is LoanCalculatorUiState.Success -> Success()
        }
    }
}

@Composable
private fun Loading() {
    Text(text = "Loading")
}
@Composable
private fun Error() {
    Text(text = "Error")
}

@Composable
private fun Success() {
    Text(text = "Success")
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    Error()
}

@Preview(showBackground = true)
@Composable
fun SuccessPreview() {
    Success()
}
