package com.ucne.parcial2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.parcial2.ui.theme.Parcial2Theme
import com.ucne.parcial2.ui.tickets.TicketsScreen
import com.ucne.parcial2.ui.tickets.TicktetListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Start.route
                    ) {
                        composable(Screen.Start.route) {
                            DrawerMenu(navController = navController)
                        }
                        composable(Screen.TicketsList.route) {
                            TicktetListScreen(onNewTicket = {}, navController = navController)
                        }
                        composable(Screen.Tickets.route) {
                            TicketsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    navController: NavController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
    val ic  = Icons.TwoTone.Favorite

    val items = listOf(Screen.Start, Screen.Tickets, Screen.TicketsList)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            navController.navigate(item.route)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {

        TopAppBar(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 10.dp, vertical = 8.dp),

            title = {
                Box(
                    modifier = Modifier,
                    Alignment.Center
                ) {

                }
            },
            navigationIcon = {
                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Icon"
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {}
            }
        )
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Start : Screen("ui","Inicio", Icons.TwoTone.Cottage)
    object Tickets : Screen("tickets", "Registro de Tickets", Icons.TwoTone.Receipt)
    object TicketsList : Screen("tickets_list","Lista de Tickets", Icons.TwoTone.ReceiptLong)
}
