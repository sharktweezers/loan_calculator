package dsokolov.ru.loan_calculator.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelFactoryHolder
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorViewModel
import dsokolov.ru.loan_calculator.ui.theme.GRID_2
import dsokolov.ru.loan_calculator.ui.theme.GRID_8

@Composable
fun LoanCalculatorScreen(
    loanCalculatorViewModel: LoanCalculatorViewModel = viewModel(
        factory = ViewModelFactoryHolder.store.viewModelFactory
    )
) {
    Column(Modifier.padding(GRID_2.dp)) {
        val uiState by loanCalculatorViewModel.stateFlow.collectAsStateWithLifecycle()
        when (uiState) {
            is LoanCalculatorState.Empty -> Unit
            is LoanCalculatorState.Loading -> Loading()
            is LoanCalculatorState.FilledLoanCalculatorState -> Success()
        }
    }
}

@Composable
private fun Loading() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(GRID_8.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
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
