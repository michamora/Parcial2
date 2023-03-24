package com.ucne.parcial2.ui.tickets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.ucne.parcial2.Screen
import com.ucne.parcial2.data.remote.dto.TicketDto
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(
    navController: NavController,
    viewModel: TicketViewModel = hiltViewModel(),
    onTicketClick: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        Spacer(Modifier.height(10.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCD8595FF),
            modifier = Modifier
                .align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Screen.Start.route)
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
                    .padding()
            ) {
                TicketListBody(uiState.tickets){
                    onTicketClick(it)
                }
            }
        }
    }


@Composable
fun TicketListBody(ticketList: List<TicketDto>, onTicketClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) {ticket ->
                TicketRow(ticket)
                {
                    onTicketClick(it)
                }
            }
        }
    }
}

@Composable
fun TicketRow(ticket: TicketDto, onTicketClick: (Int) -> Unit) {
    Spacer(modifier = Modifier.padding(10.dp))

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onTicketClick(ticket.ticketId) })
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                Text(
                    text = ticket.empresa,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(7f)


                )
                Text(
                    text = "  "+ ticket.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xD0808080),
                    modifier = Modifier.weight(2f).border(0.5.dp, Color(0x56808080), shape = RoundedCornerShape(14.dp)),

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
                    imageVector = when (ticket.estatus) {
                        "Solicitado" -> {
                            Icons.TwoTone.Star


                        }
                        "En espera" -> {
                            Icons.TwoTone.PendingActions

                        }
                        else -> {
                            Icons.TwoTone.AssignmentTurnedIn
                        }
                    },
                    contentDescription = ticket.estatus,
                    modifier = Modifier.size(40.dp, 40.dp),

                    tint = when (ticket.estatus) {
                        "Solicitado" -> {
                            Color(0xFF699AFC)
                        }
                        "En espera" -> {
                            Color(0xFFF3E66A)
                        }
                        else -> {
                            Color(0xFF85F171)
                        }
                    }
                )

             }
            }
            Divider(Modifier.fillMaxWidth())
        }
    }
