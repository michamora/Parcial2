package com.ucne.parcial2.ui.ticket

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(viewModel: TicketViewModel = hiltViewModel()) {
    TicketBody(viewModel)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TicketBody(
    viewModel: TicketViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.asunto,
            onValueChange = { viewModel.asunto = it },
            label = { Text("Asunto") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.empresa,
            onValueChange = { viewModel.empresa = it },
            label = { Text("Empresa") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.especificaciones,
            onValueChange = { viewModel.especificaciones = it },
            label = { Text("Especificaciones") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.estatus,
            onValueChange = { viewModel.estatus = it },
            label = { Text("Estatus") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.fecha,
            onValueChange = { viewModel.fecha = it },
            label = { Text("Fecha") }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = { viewModel.insertar() }
        )
    }
}