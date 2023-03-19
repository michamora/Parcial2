package com.ucne.parcial2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.parcial2.ui.theme.Parcial2Theme
import com.ucne.parcial2.ui.tickets.AcercadeScreen
import com.ucne.parcial2.ui.tickets.TicketsScreen
import com.ucne.parcial2.ui.tickets.TicketListScreen
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
                        composable(route = Screen.TicketsList.route) {
                            TicketListScreen(navController = navController) { id ->
                                navController.navigate(Screen.Tickets.route + "/${id}")
                            }
                        }
                        composable(
                            route = Screen.Tickets.route + "/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { capturar ->
                            val ticketId = capturar.arguments?.getInt("id") ?: 0

                            TicketsScreen(ticketId = ticketId, navController = navController) {
                                navController.navigate(Screen.TicketsList.route)
                            }
                        }

                        composable(Screen.Acercade.route) {
                            AcercadeScreen(navController = navController)
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

        val items = listOf(Screen.Start, Screen.TicketsList, Screen.Acercade)
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

            },

            content = {

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    painter = painterResource(id = R.drawable.ticket),
                    contentDescription = "Imagen logo"
                )
                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(Modifier.height(30.dp))
                    Button(onClick = { scope.launch { drawerState.open() } }) {
                        Text("Menu ")
                        Icon(imageVector = Icons.TwoTone.ViewHeadline, contentDescription = "Save")

                    }
                }
            }
        )
    }

    sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
        object Start : Screen("ui", "Inicio", Icons.TwoTone.Cottage)
        object Tickets : Screen("tickets", "Registro de Tickets", Icons.TwoTone.Receipt)
        object TicketsList : Screen("tickets_list", "Lista de Tickets", Icons.TwoTone.ReceiptLong)
        object Acercade : Screen("acercade", "Acerca de", Icons.TwoTone.Info)
    }

