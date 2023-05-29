package com.example.currencyconverter.currencyFeature

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.view.*
import com.example.currencyconverter.utils.ViewState
import com.example.currencyconverter.utils.ViewStateForConversion


@Composable
fun CategoriesScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewStateObject by viewModel.viewState.observeAsState()

    DisposableEffect(key1 = Unit) {
        if (!(Constants.APP_ID.isNullOrBlank())) {
            viewModel.getCurrencies(Constants.APP_ID)
        }

        onDispose { }
    }


    var modifier: Modifier = Modifier



    Column(

    ) {

        when (viewStateObject) {


            is ViewState.Error -> Text(
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,

                text = (viewStateObject as ViewState.Error).errorMessage
            )

            ViewState.Loading -> Text(text = "Loading")

            is ViewState.Success -> {
                val data = (viewStateObject as ViewState.Success).data
                val result = data.rates
//                val list: List<String> = result?.keys?.toList() ?: emptyList()


                AmountInputTextField(viewModel)


                DropDownForCurrencySelector(
                    result!!,
                    modifier,
                    viewModel
                )

                if (
                    viewModel.amountState.collectAsState().value?.isNotEmpty() == true &&
                    viewModel.currencyState.collectAsState().value?.isNotEmpty() == true
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(result?.toList() ?: emptyList()) { item ->

                            if (
                                viewModel.amountState.collectAsState().value?.isNotEmpty() == true &&
                                viewModel.currencyState.collectAsState().value?.isNotEmpty() == true
                            ) {

                                SingleItemCategory(item, viewModel)
                            }
                        }
                    }
                }


            }
            null -> {
                Text(text = (viewStateObject as ViewState.Error).errorMessage)
            }
        }
    }


}

@Composable
fun SingleItemCategory(item: Pair<String, String>, viewModel: HomeViewModel) {

    val paddingModifier = Modifier.padding(10.dp)

    viewModel.conversionUseCaseMethod(
        viewModel.amountState.collectAsState().value.toDouble(),
        viewModel.dividerAmountState.collectAsState().value.toDouble(),
        item.second.toDouble(),
        item.first
    )
    val mapStateObject by viewModel.viewStateForConversion.observeAsState()

    when (mapStateObject) {
        is ViewStateForConversion.Error -> {
            Text("Unkown Error Occurred")
        }
        ViewStateForConversion.Loading -> {
            Text("Loading")
        }
        is ViewStateForConversion.Success -> {
            val item = (mapStateObject as ViewStateForConversion.Success).data
            item.forEach { item ->

                Card(
                    modifier = paddingModifier
                        .width(60.dp)
                        .height(80.dp),
                ) {
                    Column(modifier = paddingModifier) {
                        HorizontalCenteredRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)


                        ) {

                            Text(
                                text = item.key,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                            )

                        }
                        HorizontalCenteredRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)


                        ) {

                            Text(
                                text = item.value,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,

                                )
                        }
                    }

                }
            }
        }
        null -> {
            Text("Null Error occurred")
        }
    }


}

@Composable
fun DropDownForCurrencySelector(
    list: Map<String, String>,
    boxScope: Modifier,
    viewModel: HomeViewModel
) {
    HorizontalEndRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 50.dp)


    ) {
        DropDownMenu(
            list = list,
            homeViewModel = viewModel
        )

    }
}



