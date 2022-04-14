package com.example.capwayscreen.ui.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capwayscreen.R
import com.example.capwayscreen.models.ListItem
import com.example.capwayscreen.repos.MockRepo
import com.example.capwayscreen.ui.menu_categories.Categories
import com.example.capwayscreen.ui.menu_categories.getAllSelectionCategories

/*
 * Account screen main composable. All screen elements can be added or removed
 * with modification of this composable.
 */
@Preview
@Composable
fun AccountScreen() {
    Scaffold {
        TopNavScrollMenu()
        TransactionTabs()
    }
}

@Composable
fun AccountBalance() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .offset(30.dp, 60.dp)

        ) {
            Text(
                text = "$255.73",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold

            )
            Text(
                text = "Account Balance",
                fontSize = 15.sp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .offset(0.dp, 20.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Pending Charges", fontSize = 15.sp)
                Text(
                    modifier = Modifier.offset(180.dp),
                    text = "$0.00", color = Color.Gray,
                    textAlign = TextAlign.Right,
                )
            }
            Divider(
                modifier = Modifier.offset((-30).dp, 30.dp)
            )
            Row(
                modifier = Modifier.offset(0.dp, 40.dp)
            ) {
                Text(
                    text = "CapWay Card",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.offset(10.dp, 0.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset((-20).dp, 32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cardlg),
                    contentDescription = "Credit card Image",
                    modifier = Modifier.weight(2f, true)
                )
                Box(modifier = Modifier.weight(3f, true)) {
                    Text(
                        text = "Card 2888 is inactive",
                        fontSize = 15.sp,
                        modifier = Modifier.offset(0.dp, 20.dp)
                    )
                    Text(
                        text = "Virtual Card Add Funds",
                        color = colorResource(id = R.color.light_blue_capway),
                        modifier = Modifier.offset(0.dp, 50.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TopNavScrollMenu() {
    var selectedItem by remember {
        mutableStateOf("Transactions")
    }
    val onSelectionChange = { text: String ->
        selectedItem = text
    }

    Column {
        Spacer(modifier = Modifier.padding(2.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .horizontalScroll(
                    rememberScrollState()
                )
                .fillMaxSize(),
        ) {
            getAllSelectionCategories().forEach { text ->
                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 2.dp
                        ),
                ) {
                    Text(
                        text = text.value,
                        style = typography.body1.merge(),
                        color = (
                                if (text.value == selectedItem) {
                                    Color.White
                                } else {
                                    Color.DarkGray
                                }

                                ),

                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(
                                    size = 25.dp,
                                ),
                            )
                            .clickable {
                                onSelectionChange(text.value)
                            }
                            .background(
                                if (text.value == selectedItem) {
                                    colorResource(id = R.color.light_blue_capway)
                                } else {
                                    MaterialTheme.colors.background
                                }
                            )
                            .padding(
                                vertical = 2.dp,
                                horizontal = 10.dp,
                            ),
                    )
                }
            }
        }
    }
    SelectScrollMenuView(selectedItem = selectedItem)
}

@Composable
fun SelectScrollMenuView(selectedItem: String) {
    when (selectedItem) {
        Categories.TRANSACTIONS.value -> {
            AccountBalance()
        }
        else -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

            }
        }
    }
}

@Composable
fun TransactionTabs() {
    val headers: List<String> = listOf("Transactions", "Deposits", "Withdrawals")
    var selectedIndex by remember { mutableStateOf(0) }
    var color: Color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(0.dp, 320.dp)
    ) {
        TabRow(
            contentColor = colorResource(id = R.color.light_blue_capway),
            selectedTabIndex = selectedIndex,
            backgroundColor = Color.White,

            ) {
            headers.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = {
                        color = if (selectedIndex == index) {
                            Color.Black
                        } else {
                            Color.LightGray
                        }
                        Text(
                            text = title,
                            color = color
                        )
                    },

                    )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            Alignment.Center
        ) {
            SelectedTabContent(headers[selectedIndex])
        }
    }
}

@Composable
fun SelectedTabContent(index: String = "Transactions") {
    when (index) {
        "Transactions" -> {
            TransactionView()
        }
        "Deposits" -> {
            Text(text = "Selected : $index")
        }
        "Withdrawals" -> {
            Text(text = "Selected : $index")
        }
    }
}

@Composable
fun TransactionView() {
    val searchVal = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row {
            SearchField(searchItem = searchVal)
            //SearchTransactionList(searchItem = searchVal)
        }
        Row(Modifier.height(310.dp)) {
            TransactionListView(searchVal)
        }
    }
}

@Composable
fun SearchField(searchItem: MutableState<TextFieldValue>) {
    BasicTextField(
        value = searchItem.value,
        singleLine = true,
        onValueChange = { searchItem.value = it },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp,
        ), modifier = Modifier
            .padding(10.dp)
            .height(45.dp)
            .border(
                BorderStroke(2.dp, SolidColor(Color.LightGray)),
                shape = RoundedCornerShape(45.dp)
            ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                if (searchItem.value == TextFieldValue("")) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "Search",
                        color = Color.LightGray,
                        fontSize = 18.sp,
                        modifier = Modifier.offset(x = 0.dp, y = 9.dp)

                    )
                } else {
                    IconButton(
                        onClick = {
                            searchItem.value = TextFieldValue("")
                        },
                        modifier = Modifier
                            .offset(x = 320.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.transact
                            ),
                            contentDescription = "Close",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .offset(x = 40.dp, y = 10.dp)
            ) {
                innerTextField()
            }
        }
    )
}

@Composable
fun TransactionListView(textVal: MutableState<TextFieldValue>) {
    val mockRepo = MockRepo()
    val transactions = mockRepo.getItems()
    var filteredTransactions: List<ListItem>

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        val searchText = textVal.value.text
        filteredTransactions = if (searchText.isEmpty()) {
            transactions
        } else {
            val filterList = mutableListOf<ListItem>()
            transactions.forEach { item ->
                if (item.institutionName.lowercase().contains(searchText)) {
                    filterList.add(item)
                }
            }
            filterList
        }
        items(filteredTransactions) { filteredTransactions ->
            ListItemView(
                item = filteredTransactions
            )
        }
    }
}

@Composable
fun ListItemView(item: ListItem) {
    val context = LocalContext.current
    val debitCreditColor = if (
        item.transactionAmount.contains("-")
    ) Color.Red else Color.Green
    Row(
        Modifier
            .clickable(
                onClick = Toast
                    .makeText(
                        context,
                        "Contact Derek to add additional functions",
                        Toast.LENGTH_SHORT
                    )::show
            )
            .wrapContentSize()
            .height(60.dp)
            .padding(4.dp)
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Image(
                painter = painterResource(id = R.drawable.money/*item.image*/),
                contentDescription = "Image",
                modifier = Modifier
                    .offset(20.dp, 0.dp)

            )
        }
        Column {
            Row {
                Text(
                    text = item.institutionName,
                    fontSize = 14.sp,
                    color = Color.Black,

                    modifier = Modifier
                        .offset(30.dp, (2).dp),
                    textAlign = TextAlign.Start
                )
            }
            Row {
                Text(
                    text = item.transactionDate,
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .offset(30.dp, 4.dp),
                    textAlign = TextAlign.Start
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = item.transactionAmount,
                fontSize = 14.sp,
                color = debitCreditColor,
                modifier = Modifier
                    .offset((-20).dp, 2.dp),
                textAlign = TextAlign.End
            )
            Text(
                text = item.transactionStatus,
                fontSize = 11.sp,
                color = Color.LightGray,
                modifier = Modifier
                    .offset((-20).dp, 2.dp)
            )
        }

    }
    Divider()
}
