package com.ucne.parcial2.ui.tickets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.parcial2.Screen

import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AcercadeScreen(viewModel: TicketViewModel = hiltViewModel(), navController: NavController) {

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(Modifier.height(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCD8595FF),
            modifier = Modifier.align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Screen.Start.route)
                    }
                }
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.ArrowCircleLeft,
                tint = Color(0xCD8595FF),
                contentDescription = null,

                )
            Text(
                text = ": Icono para retroceder a la ventana anterior.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.AddCircle,
                tint = Color(0xFFFA7146),
                contentDescription = null,

                )
            Text(
                text = ": Icono para registrar nuevo ticket.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.Delete,
                tint = Color(0xFFEB0909),
                contentDescription = null,

                )
            Text(
                text = ": Icono para eliminar ticket.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {

            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.CleaningServices,
                tint = Color(0xFF49F19A),
                contentDescription = null,

                )
            Text(
                text = ": Icono para limpiar los campos.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )

        }

        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Estatus:", fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = ""
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.PendingActions,
                tint = Color(0xFFF3E66A),
                contentDescription = null,

                )
            Text(
                text = ": En espera.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.Star,
                tint = Color(0xFF699AFC),
                contentDescription = null,

                )
            Text(
                text = ": Solicitado.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {

            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.AssignmentTurnedIn,
                tint = Color(0xFF85F171),
                contentDescription = null,

                )
            Text(
                text = ": Finalizado.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }

        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = " Nota: Necesitas conección a internet para el funcionamiento de la aplicacion.", fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Spacer(modifier = Modifier.padding(8.dp))
        Divider(Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = " Segundo parcial programacion aplicada 2, Modificar Tickets con Api.", fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "by Michael Mora ©", fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}