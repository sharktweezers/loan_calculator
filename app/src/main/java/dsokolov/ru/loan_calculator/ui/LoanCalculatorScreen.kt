package dsokolov.ru.loan_calculator.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelFactoryHolder
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorUiSideEffect
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorUiState
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorViewModel
import dsokolov.ru.loan_calculator.ui.theme.GRID_1
import dsokolov.ru.loan_calculator.ui.theme.GRID_1_5
import dsokolov.ru.loan_calculator.ui.theme.GRID_2
import dsokolov.ru.loan_calculator.ui.theme.GRID_4
import dsokolov.ru.loan_calculator.ui.theme.GRID_6
import dsokolov.ru.loan_calculator.ui.theme.GRID_8
import dsokolov.ru.loan_calculator.ui.theme.GRID_HALF
import dsokolov.ru.loan_calculator.ui.theme.LimeDeep
import dsokolov.ru.loan_calculator.ui.theme.LimeLight
import dsokolov.ru.loan_calculator.ui.theme.OrangeDeep
import dsokolov.ru.loan_calculator.ui.theme.OrangeLime

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
            onApplyClick = loanCalculatorViewModel::onApplyClick,
        )
    }

    val ctx = LocalContext.current
    LaunchedEffect(Unit) {
        loanCalculatorViewModel.sideEffectFlow.collect { event ->
            when (event) {
                is LoanCalculatorUiSideEffect.SuccessTransaction -> {
                    Toast.makeText(ctx, event.msg, Toast.LENGTH_LONG).show()
                }
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilledScreen(
    state: LoanCalculatorUiState.FilledLoanCalculatorState,
    onAmountSliderChanged: (Float) -> Unit,
    onDaysPeriodSliderChanged: (Float) -> Unit,
    onApplyClick: () -> Unit
) {
    if (state.transaction == LoanCalculatorTransaction.Loading) {
        Loading()
    }

    Column(
        Modifier
            .padding(GRID_2.dp)
            .fillMaxSize()
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 1,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(GRID_4.dp))

        AmountBlock(
            state = state,
            onAmountSliderChanged = onAmountSliderChanged,
        )

        PeriodBlock(
            state = state,
            onDaysPeriodSliderChanged = onDaysPeriodSliderChanged,
        )

        LoanInfoBlock(state = state)

        Error((state.transaction as? LoanCalculatorTransaction.Error)?.error)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.clickable(
                    onClick = {},
                    indication = ripple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() },
                ),
                onClick = { onApplyClick.invoke() },
                shape = RoundedCornerShape(GRID_1_5.dp)
            ) {
                Text(
                    text = state.applyBtnTitle,
                    maxLines = 1,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmountBlock(
    state: LoanCalculatorUiState.FilledLoanCalculatorState,
    onAmountSliderChanged: (Float) -> Unit,
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = state.amountTitle,
            maxLines = 1,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = state.amountValue,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge,
        )
    }

    Spacer(Modifier.height(GRID_1.dp))

    LoanCalculatorSlider(
        sliderValue = state.amount,
        steps = state.maxRangeAmount - state.minRangeAmount - 2,
        valueRange = state.minRangeAmount.toFloat()..state.maxRangeAmount.toFloat(),
        onValueChanged = onAmountSliderChanged,
        colorLight = LimeLight,
        colorDeep = LimeDeep,
    )

    Spacer(Modifier.height(GRID_HALF.dp))

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = state.minRangeAmount.toString(),
            maxLines = 1,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = state.maxRangeAmount.toString(),
            maxLines = 1,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
    Spacer(Modifier.height(GRID_6.dp))
}

@Composable
private fun PeriodBlock(
    state: LoanCalculatorUiState.FilledLoanCalculatorState,
    onDaysPeriodSliderChanged: (Float) -> Unit,
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.daysPeriodTitle,
            maxLines = 1,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = state.daysPeriodValue,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
    Spacer(Modifier.height(GRID_1.dp))

    LoanCalculatorSlider(
        sliderValue = state.daysPeriod,
        steps = state.stepCountDaysPeriod - 2,
        valueRange = state.minRangeDaysPeriod.toFloat()..state.maxRangeDaysPeriod.toFloat(),
        onValueChanged = onDaysPeriodSliderChanged,
        colorLight = OrangeLime,
        colorDeep = OrangeDeep,
    )

    Spacer(Modifier.height(GRID_HALF.dp))

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.minRangeDaysPeriod.toString(),
            maxLines = 1,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = state.maxRangeDaysPeriod.toString(),
            maxLines = 1,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
    Spacer(Modifier.height(GRID_6.dp))
}

@Composable
fun LoanInfoBlock(state: LoanCalculatorUiState.FilledLoanCalculatorState) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.interestRateTitle,
            maxLines = 1,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = state.interestRateValue,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
    Spacer(Modifier.height(GRID_4.dp))
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.loanRepaymentAmountTitle,
            maxLines = 1,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = state.loanRepaymentAmountValue,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
    Spacer(Modifier.height(GRID_4.dp))
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.repaymentDateTitle,
            maxLines = 1,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = state.repaymentDateValue,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
    Spacer(Modifier.height(GRID_4.dp))
}

@Composable
private fun Error(error: String?) {
    error?.let { error ->
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = error,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2,
                textAlign = TextAlign.Center,
                color = Color.Red,
            )
        }

        Spacer(Modifier.height(GRID_4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
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
            transaction = LoanCalculatorTransaction.Success,
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
        onApplyClick = {},
    )
}
