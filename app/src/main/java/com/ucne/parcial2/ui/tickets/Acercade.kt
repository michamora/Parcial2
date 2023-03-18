package com.ucne.parcial2.ui.tickets

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.twotone.ArrowCircleLeft
import androidx.compose.material.icons.twotone.PendingActions
import androidx.compose.material.icons.twotone.PostAdd
import androidx.compose.material.icons.twotone.Save
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
            modifier = Modifier.align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Screen.Start.route)
                    }
                }
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(40.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.ArrowCircleLeft,

                contentDescription = null,

                )
            Text(
                text = ": Icono para retroceder a la ventana anterior.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(40.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.PostAdd,

                contentDescription = null,

                )
            Text(
                text = ": Icono para registrar nuevo ticket.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Divider(Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = " Segundo parcial programacion aplicada 2, Registro de Tickets con Api.", fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "by Michael Mora ©", fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}