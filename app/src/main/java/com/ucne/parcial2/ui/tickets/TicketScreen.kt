package com.ucne.parcial2.ui.tickets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketsScreen(viewModel: TicketViewModel = hiltViewModel(), navController: NavController) {
    TicketsBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketsBody(
    viewModel: TicketViewModel, modifier: Modifier, navController: NavController
) {
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(Modifier.height(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Screen.Start.route)
                    }
                }
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Tickets", fontSize = 27.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.asunto,
            onValueChange = viewModel::onAsuntoChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.ReceiptLong,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Asunto") },
            isError = viewModel.asuntoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.asuntoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.asuntoError.isNotBlank()) {
            Text(
                text = viewModel.asuntoError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.empresa,
            onValueChange = viewModel::onEmpresaChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Domain,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Empresa") },
        isError = viewModel.empresaError.isNotBlank(),
        trailingIcon = {
            if (viewModel.empresaError.isNotBlank()) {
                Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
            }
        }
        )
        if (viewModel.empresaError.isNotBlank()) {
            Text(
                text = viewModel.empresaError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.especificaciones,
            onValueChange = viewModel::onEspecificacionesChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.EmojiObjects,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Especificaciones") },
            isError = viewModel.especificacionesError.isNotBlank(),
            trailingIcon = {
                if (viewModel.especificacionesError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.especificacionesError.isNotBlank()) {
            Text(
                text = viewModel.especificacionesError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.encargadoId,
            onValueChange = viewModel::onEncargadoChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.AssignmentInd,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Encargado") },
            isError = viewModel.encargadoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.encargadoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.encargadoError.isNotBlank()) {
            Text(
                text = viewModel.encargadoError,
                color = MaterialTheme.colorScheme.error
            )
        }



        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.fecha,
            onValueChange = { viewModel.fecha = it },
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                        .clickable {

                        })
            },
            label = { Text(text = "Fecha") }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Refresh") },
                contentColor = Color(0xFFFFFFFF),
                containerColor = Color(0xFF8595FF),
                icon = { Icon(imageVector = Icons.TwoTone.Refresh, contentDescription = "Save") },
                onClick = {
                    viewModel.limpiar()
                }
            )
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Guardar") },
                contentColor = Color(0xFF444444),
                containerColor = Color(0xFFB1E4B2),
                icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Save") },
                onClick = {
                    viewModel.insertar()
                }
            )

        }
    }
}
