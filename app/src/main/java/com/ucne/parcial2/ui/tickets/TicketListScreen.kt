package com.ucne.parcial2.ui.tickets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucne.parcial2.Screen
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.ui.theme.Parcial2Theme
import com.ucne.parcial2.ui.theme.Purple40


import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicktetListScreen(onNewTicket: () -> Unit, viewModel: TicketApiViewModel = hiltViewModel(), navController: NavController) {
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        Spacer(Modifier.height(10.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Screen.Start.route)
                    }
                }
        )
            Icon(
                imageVector = Icons.TwoTone.PostAdd,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize(Alignment.TopStart)
                    .size(50.dp, 50.dp)
                    .clickable {
                        scope.launch {
                            navController.navigate(Screen.Tickets.route)
                        }
                    }
            )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = "Lista de Tickets", fontSize = 27.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )


            val uiState by viewModel.uiState.collectAsState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TicketListBody(uiState.tickets)
            }
        }
    }


@Composable
fun TicketListBody(ticketList: List<TicketDto>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) {ticket ->
                TicketRow(ticket)
            }
        }
    }
}

@Composable
fun TicketRow(ticket: TicketDto) {
    Spacer(modifier = Modifier.padding(10.dp))

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
        ){
            Text(
                text = ticket.empresa,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF000000),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(7f)


            )
            Text(
                text = ticket.fecha.format("dd/mm/yyyy"),
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xD0808080),
                modifier = Modifier.weight(4f)

            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Text(
                text = ticket.asunto,
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xC3303030),
                modifier = Modifier.weight(8f)
            )

            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.PendingActions,

                contentDescription = null,

            )
            Text(
                text = ticket.estatus,
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFFF7C05E),
                modifier = Modifier.weight(4f)
            )



        }
        Divider(Modifier.fillMaxWidth())
    }
}