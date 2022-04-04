package com.example.capwayscreen.ui.menu_categories

enum class Categories (val value: String){
    TRANSACTIONS("Transactions"),
    MONEY_GOALS("Money Goals"),
    ACTIVITY("Activity"),
    STATEMENTS("Statements"),
    OPTIONAL("Option 1"),
    OPTIONAL2("Option 2"),
    OPTION3("Option 3")
}

fun getAllSelectionCategories(): List<Categories>
{
    return Categories.values().toList()
}