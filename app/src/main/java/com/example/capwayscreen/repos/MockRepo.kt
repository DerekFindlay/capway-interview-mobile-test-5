package com.example.capwayscreen.repos

import com.example.capwayscreen.R
import com.example.capwayscreen.models.ListItem

class MockRepo() {

    fun getItems(): List<ListItem> {
        return generateRandom()
    }

    //This function generates a list of items that will be used to populate the lazyColumn.
    //Notice: that in a production app this would be accessible vai the ViewModel. For demonstration
    //purposes this step is skipped.
    private fun generateRandom(): List<ListItem> {
        val items = mutableListOf<ListItem>()
        for (item in 1..100) {
            items.add(
                ListItem(
                    retailers.random(),
                    (0..10).random().toString().padStart(2, '0') +
                            "/${(0..28).random()}/20${(21..22).random()} at ${
                                (1..12).random().toString().padStart(2, '0')
                            }:${(0..59).random().toString().padStart(2, '0')} ${amOrPm.random()}",


                    "${depositOrDebit.random()}$${(1..100).random().toString().padStart(2, '0')}." +
                            (0..99).random().toString().padStart(2, '0'),
                    R.drawable.money,
                    pendingTransactionList.random()
                )
            )
        }
        return items.toList()
    }

    private val retailers = listOf(
        "Macy's",
        "Walmart",
        "Amazon",
        "Krogers",
        "The Home Depot",
        "Costco Wholesale",
        "Target",
        "CVS",
        "Lowes",
        "Apple Store",
        "Derek's Houes of Pancakes",
        "Best Buy",
        "Aldi",
        "Dollar General",
        "Dominos",
        "TJ Max",
        "7-Eleven",
        "Verizon",
        "Rite Aid",
        "PetSmart",
        "Wayfair",
        "AutoZone",
        "Giant Eagle",
        "Dick's Sporting Goods"
    )

    //Weighted List
    private val depositOrDebit = listOf("+", "-", "-", "-", "-", "-")
    private val pendingTransactionList = listOf("Pending", "", "", "", "")
    private val amOrPm = listOf("AM", "PM", "PM", "PM", "PM")

}