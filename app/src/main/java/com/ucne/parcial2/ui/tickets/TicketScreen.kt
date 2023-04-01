package com.ucne.parcial2.ui.tickets


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.parcial2.Screen
import kotlinx.coroutines.launch
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsScreen(
    ticketId: Int,
    navController: NavController,
    viewModel: TicketViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    remember {
        viewModel.setTicket(ticketId)
        0
    }

    val scope = rememberCoroutineScope()
    var expanded by remember {
        mutableStateOf(false)
    }


    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(Modifier.height(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCD8595FF),
            modifier = Modifier
                .align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Screen.TicketsList.route)
                    }
                }
        )


        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Tickets", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.empresa,
            onValueChange = viewModel::onEmpresaChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Domain,
                    contentDescription = null,
                    tint = Color(0xFF94B4F5),
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
            value = viewModel.asunto,
            onValueChange = viewModel::onAsuntoChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.ReceiptLong,
                    contentDescription = null,
                    tint = Color(0xFFF56379),
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


        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.especificaciones,
            onValueChange = viewModel::onEspecificacionesChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.EmojiObjects,
                    contentDescription = null,
                    tint = Color(0xFFFFE980),
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


        OutlinedTextField(   //Estatus
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { expanded = true },
            value = viewModel.estatus,
            enabled = false, readOnly = true,
            onValueChange = viewModel::onEstatusChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.ContentPasteGo,
                    contentDescription = null,
                    tint = Color(0xFFF89945),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            isError = viewModel.tipoEstatusError.isNotBlank(),
            label = { Text("Estatus") },
            trailingIcon = {
                if (viewModel.tipoEstatusError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                if (viewModel.tipoEstatusError.isBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = Color(0xFF8A8A8A)
                    )
                }

            }
        )

        if (viewModel.tipoEstatusError.isNotBlank()) {
            Text(
                text = viewModel.tipoEstatusError,
                color = MaterialTheme.colorScheme.error,

                )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)

        ) {
            viewModel.tipoEstatus.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(text = opcion, textAlign = TextAlign.Center)
                    },
                    onClick = {
                        expanded = false
                        viewModel.estatus = opcion
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier      //Boton Clean
                .fillMaxWidth()
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Clean") },
                contentColor = Color(0xFFFFFFFF),
                containerColor = Color(0xFF8595FF),
                icon = {
                    Icon(
                        imageVector = Icons.TwoTone.CleaningServices,
                        contentDescription = "Clean"
                    )
                },
                onClick = {
                    viewModel.Clean()

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

                    if (viewModel.HayErroresModificando()) {

                        viewModel.empresaError = ""
                        if (viewModel.empresa.isBlank()) {
                            viewModel.empresaError = "  Debe indicar la empresa"
                        }

                        viewModel.asuntoError = ""
                        if (viewModel.asunto.isBlank()) {
                            viewModel.asuntoError = "  Debe indicar el asunto"
                        }

                        viewModel.especificacionesError = ""
                        if (viewModel.especificaciones.isBlank()) {
                            viewModel.especificacionesError = "  Debe indicar las especificaciones"
                        }

                        viewModel.tipoEstatusError = ""
                        if (viewModel.estatus.isBlank()) {
                            viewModel.tipoEstatusError = "  Debe seleccionar un estatus"
                        }

                    } else {
                        viewModel.putTicket()
                        navController.navigate(Screen.TicketsList.route)
                        onSaveClick()

                    }
                }
            )

        }
    }
}

