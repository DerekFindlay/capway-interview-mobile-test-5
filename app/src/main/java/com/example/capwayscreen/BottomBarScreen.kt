package com.example.capwayscreen

// This class is used to create the menu items on the BottomAppBar.
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object AccountScreen : BottomBarScreen(
        route = "accountScreen",
        title = "Account",
        icon = R.drawable.boxmoneyon
    )

    object SendScreen : BottomBarScreen(
        route = "sendScreen",
        title = "Send",
        icon = R.drawable.sendon
    )

    object MoreScreen : BottomBarScreen(
        route = "moreScreen",
        title = "",
        icon = R.drawable.more_selected_edit
    )

    object ContentScreen : BottomBarScreen(
        route = "learnScreen",
        title = "Learn",
        icon = R.drawable.learnon
    )

    object PhundsScreen : BottomBarScreen(
        route = "phundsScreen",
        title = "Phunds",
        icon = R.drawable.phundon
    )

}
