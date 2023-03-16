package com.ucne.parcial2.ui.ticket


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.parcial2.data.remote.dto.TicketDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(onNewTicket: () -> Unit, viewModel: TicketViewModel2 = hiltViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = { Text("Tickets", style = MaterialTheme.typography.headlineLarge) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewTicket() }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Save")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            TicketListBody(uiState.ticket)
        }
    }
}

@Composable
fun TicketListBody(ticketList: List<TicketDto>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) { ticket ->
                TicketRow(ticket)
            }
        }
    }
}

@Composable
fun TicketRow(ticket: TicketDto) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        //todo : Implementar swipe to delete


        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = ticket.asunto,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                String.format("%.2f", ticket.empresa),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}