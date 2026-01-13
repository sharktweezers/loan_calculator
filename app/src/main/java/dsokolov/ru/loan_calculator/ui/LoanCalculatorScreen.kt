package dsokolov.ru.loan_calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelFactoryHolder
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorUiState
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorViewModel
import dsokolov.ru.loan_calculator.ui.theme.GRID_2
import dsokolov.ru.loan_calculator.ui.theme.GRID_4
import dsokolov.ru.loan_calculator.ui.theme.GRID_6
import dsokolov.ru.loan_calculator.ui.theme.GRID_8

@Composable
fun LoanCalculatorScreen(
    loanCalculatorViewModel: LoanCalculatorViewModel = viewModel(
        factory = ViewModelFactoryHolder.store.viewModelFactory
    )
) {
    val uiState by loanCalculatorViewModel.stateFlow.collectAsStateWithLifecycle()
    when (val state = uiState) {
        is LoanCalculatorUiState.Empty -> Unit
        is LoanCalculatorUiState.Loading -> Loading()
        is LoanCalculatorUiState.FilledLoanCalculatorState -> FilledScreen(
            state = state,
            onAmountSliderChanged = loanCalculatorViewModel::onAmountSliderChanged,
            onDaysPeriodSliderChanged = loanCalculatorViewModel::onDaysPeriodSliderChanged,
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilledScreen(
    state: LoanCalculatorUiState.FilledLoanCalculatorState,
    onAmountSliderChanged: (Float) -> Unit,
    onDaysPeriodSliderChanged: (Float) -> Unit,
) {
    Column(
        Modifier
            .padding(GRID_2.dp)
            .fillMaxSize()
    ) {
        Text(
            text = state.title,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(GRID_4.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.amountTitle)
            Text(text = state.amountValue)
        }
        Spacer(Modifier.height(GRID_4.dp))
        Slider(
            value = state.amount.toFloat(),
            onValueChange = { onAmountSliderChanged.invoke(it) },
            thumb = {
                Box(
                    modifier = Modifier
                        .size(GRID_4.dp) // Set the desired size
                        .clip(RoundedCornerShape(percent = 50)) // Make it a circle or any shape
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            // Custom Track Composable
            track = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(GRID_2.dp)
                        //.clip(MaterialTheme.shapes.medium) // Apply a shape to the track
                        //.background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            },
            steps = state.maxRangeAmount - state.minRangeAmount,
            valueRange = state.minRangeAmount.toFloat()..state.maxRangeAmount.toFloat(),
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.minRangeAmount.toString())
            Text(text = state.maxRangeAmount.toString())
        }
        Spacer(Modifier.height(GRID_6.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.daysPeriodTitle)
            Text(text = state.daysPeriodValue)
        }
        Spacer(Modifier.height(GRID_4.dp))
        Slider(
            value = state.daysPeriod.toFloat(),
            onValueChange = { onDaysPeriodSliderChanged.invoke(it) },
            thumb = {
                Box(
                    modifier = Modifier
                        .size(GRID_4.dp) // Set the desired size
                        .clip(RoundedCornerShape(percent = 50)) // Make it a circle or any shape
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            // Custom Track Composable
            track = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(GRID_2.dp)
                    //.clip(MaterialTheme.shapes.medium) // Apply a shape to the track
                    //.background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            },
            steps = state.stepCountDaysPeriod,
            valueRange = state.minRangeDaysPeriod.toFloat()..state.maxRangeDaysPeriod.toFloat(),
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.minRangeDaysPeriod.toString())
            Text(text = state.maxRangeDaysPeriod.toString())
        }
        Spacer(Modifier.height(GRID_6.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.interestRateTitle)
            Text(text = state.interestRateValue)
        }
        Spacer(Modifier.height(GRID_4.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.loanRepaymentAmountTitle)
            Text(text = state.loanRepaymentAmountValue)
        }
        Spacer(Modifier.height(GRID_4.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.repaymentDateTitle)
            Text(text = state.repaymentDateValue)
        }
        Spacer(Modifier.height(GRID_4.dp))

        /*Button(
            onClick ={},
            modifier = Modifier.align(Alignment.CenterHorizontally
            ) {
            Text(
                text = state.title,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }*/
    }
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
fun FilledPreview() {
    FilledScreen(
        state = LoanCalculatorUiState.FilledLoanCalculatorState(
            amount = 10000,
            daysPeriod = 7,
            title = "Title",
            amountTitle = "AmountTitle",
            amountValue = "10,000",
            daysPeriodTitle = "daysPeriodTitle",
            daysPeriodValue = "7",
            minRangeAmount = 5000,
            maxRangeAmount = 50000,
            minRangeDaysPeriod = 0,
            maxRangeDaysPeriod = 28,
            stepCountDaysPeriod = 4,
            isTransaction = false,
            interestRateTitle = "interestRateTitle",
            interestRateValue = "interestRateValue",
            loanRepaymentAmountTitle = "loanRepaymentAmountTitle",
            loanRepaymentAmountValue = "loanRepaymentAmountValue",
            repaymentDateTitle = "repaymentDateTitle",
            repaymentDateValue = "repaymentDateValue",
            applyBtnTitle = "applyBtnTitle",
            errorMsg = null,
        ),
        onAmountSliderChanged = {},
        onDaysPeriodSliderChanged = {},
    )
}
