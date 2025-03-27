package com.example.search.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.data.model.CurrencyInfo
import com.example.search.presentation.model.ErrorState
import com.example.search.presentation.model.FunctionalIcon
import com.example.search.presentation.model.ViewInfo

@Composable
fun CurrenciesScreen(
    viewInfoState: State<ViewInfo>,
    keyword: State<String>,
    onKeywordsChangeListener: (String) -> Unit,
    onClickBackListener: () -> Unit,
    onClickCommitSearchListener: () -> Unit,
    onClickClearKeywordListener: () -> Unit,
    onClickClearLocalCurrenciesListener: () -> Unit,
    onClickSetLocalCurrenciesListener: () -> Unit,
    onFetchLocalCurrencyListener: () -> Unit,
    onFilterCryptoCurrency: () -> Unit,
    onFilterFiatCurrency: () -> Unit,
) {
    val viewInfo = viewInfoState.value
    val showLoading = viewInfo.isLoading
    val showMatchFailedError = viewInfo.isError == ErrorState.NOT_MATCHED
    val previewMatchResult = viewInfo.searchResult.isNotEmpty() && viewInfo.isCommit != true && keyword.value.isNotEmpty()
    val showCurrencies = viewInfo.currencies.isNotEmpty()
    Scaffold(
        topBar = {
            SearchTextFieldItem(
                onKeywordsChangeListener,
                onClickBackListener,
                onClickCommitSearchListener,
                onClickClearKeywordListener
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = if (viewInfo.isLoading) Alignment.Center else Alignment.TopCenter
            ) {
                if (showLoading) {
                    LoadingComponent()
                } else if (showMatchFailedError) {
                    NoMatchConditionComponent(modifier = Modifier.padding(innerPadding), keyword)
                } else if (viewInfo.isError == ErrorState.EMPTY) {
                    EmptyScreen(modifier = Modifier.padding(innerPadding))
                } else if (previewMatchResult) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.LightGray),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(viewInfo.searchResult) {
                            CurrencyAndSymbolItem(it)
                        }
                    }
                } else if (showCurrencies) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        if (viewInfo.isCommit == true && keyword.value.isNotEmpty()) {
                            items(viewInfo.searchResult) { currency: CurrencyInfo ->
                                CurrencyAndSymbolItem(currency)
                            }
                        } else {
                            items(viewInfo.currencies) { currency: CurrencyInfo ->
                                CurrencyAndSymbolItem(currency)
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(36.dp))
                        }
                    }
                }
            }
        },
        bottomBar = {
            FunctionalIconFooter(
                onClickClearLocalCurrenciesListener,
                onClickSetLocalCurrenciesListener,
                onFetchLocalCurrencyListener,
                onFilterCryptoCurrency,
                onFilterFiatCurrency
            )
        }
    )
}

@Composable
fun FunctionalIconFooter(
    onClickClearLocalCurrenciesListener: () -> Unit,
    onClickSetLocalCurrenciesListener: () -> Unit,
    onFetchLocalCurrencyListener: () -> Unit,
    onFilterCryptoCurrency: () -> Unit,
    onFilterFiatCurrency: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        FunctionalIconComponent(FunctionalIcon.Clear, onClickClearLocalCurrenciesListener)
        FunctionalIconComponent(FunctionalIcon.Fetch, onClickSetLocalCurrenciesListener)
        FunctionalIconComponent(FunctionalIcon.Crypto, onFilterCryptoCurrency)
        FunctionalIconComponent(FunctionalIcon.Fiat, onFilterFiatCurrency)
        FunctionalIconComponent(FunctionalIcon.All, onFetchLocalCurrencyListener)
    }
}

@Composable
fun FunctionalIconComponent(functionality: FunctionalIcon, listener: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                listener.invoke()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(functionality.icon),
            contentDescription = "",
            tint = Color(0xff092D74)
        )
        Text(text = stringResource(functionality.title), fontSize = 12.sp, color = Color.DarkGray)
    }
}

@Composable
fun SearchTextFieldItem(
    onKeywordsChangeListener: (String) -> Unit,
    onClickBackListener: () -> Unit,
    onClickCommitSearchListener: () -> Unit,
    onClickClearKeywordListener: () -> Unit,
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        var textInput by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textInput,
            onValueChange = { new ->
                textInput = new
                onKeywordsChangeListener.invoke(new)
            },
            placeholder = { Text( text = textInput ) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        textInput = ""
                        onKeywordsChangeListener.invoke("")
                        onClickBackListener.invoke()
                    },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                if (textInput.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            onClickClearKeywordListener.invoke()
                            textInput = ""
                        },
                        imageVector = Icons.Default.Clear,
                        contentDescription = ""
                    )
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    onClickCommitSearchListener.invoke()
                }
            )
        )
    }
}

@Composable
fun CurrencyAndSymbolItem(currency: CurrencyInfo) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (currencyView, symbolView) = createRefs()
        Box(modifier = Modifier.constrainAs(currencyView) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(symbolView.start)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }) {
            CurrencyIconTextComponent(currency.name)
        }

        Box(modifier = Modifier.constrainAs(symbolView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        }) {
            if (currency.code.isNullOrEmpty()) {
                SymbolTextIcon(currency.symbol)
            }
        }
    }
}

@Composable
fun CurrencyIconTextComponent(currency: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            modifier = Modifier
                .background(color = Color.DarkGray, shape = CircleShape)
                .size(24.dp)
                .padding(2.dp)
                .wrapContentSize(),
            color = Color.White,
            text = currency.first().uppercase(),
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            color = Color.Black,
            text = currency,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun SymbolTextIcon(symbol: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Right
    ) {

        Text(
            color = Color.Black,
            text = symbol,
            fontSize = 16.sp
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = ""
        )

    }
}

@Composable
fun EmptyScreen(modifier: Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(com.example.resource.R.drawable.ic_empty),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(com.example.resource.R.string.ErrorState_Empty),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NoMatchConditionComponent(modifier: Modifier, keyword: State<String>) {
    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(com.example.resource.R.drawable.ic_error),
            contentDescription = null
        )
        val str = buildAnnotatedString {
            append(stringResource(com.example.resource.R.string.ErrorState_MatchFailed_Prefix))
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(" \"" + keyword.value+ "\"")
            }
            append(stringResource(com.example.resource.R.string.ErrorState_MatchFailed_Suffix))
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = str, fontSize = 24.sp, textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingComponent() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview
@Composable
fun PreviewSearchCurrencyScreen() {
    val viewInfo = remember { mutableStateOf(ViewInfo()) }
    val keyword = remember { mutableStateOf("") }
    CurrenciesScreen(viewInfo, keyword, {}, {}, {}, {}, {}, {}, {}, {}, {})
}

@Preview
@Composable
fun PreviewSearchTextFieldItem() {
    SearchTextFieldItem({}, {}, {}, {})
}

@Preview
@Composable
fun PreviewCurrencyIconTextComponent() {
    CurrencyIconTextComponent("Cucumber")
}

@Preview
@Composable
fun PreviewSymbolTextIcon() {
    SymbolTextIcon("CUC")
}

@Preview
@Composable
fun PreviewFunctionalIconFooter() {
    FunctionalIconFooter({}, {}, {}, {}, {})
}

@Preview
@Composable
fun PreviewLoadingComponent() {
    LoadingComponent()
}