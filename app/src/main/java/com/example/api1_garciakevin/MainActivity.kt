
package com.example.api1_garciakevin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api1_garciakevin.ui.theme.Api1GarciaKevinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Api1GarciaKevinTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ReminderScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ReminderScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Mis recordatorios",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Organizá tus tareas y prioridades",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "${sampleReminders.size} recordatorios",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = sampleReminders,
                key = { it.id }
            ) { reminder ->
                ReminderCard(reminder = reminder)
            }
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {
    val categoryInfo = categoryInfoMap[reminder.categoria]
    val categoryColor = categoryInfo?.colorHex?.let {
        Color(android.graphics.Color.parseColor(it))
    } ?: MaterialTheme.colorScheme.primary

    val priorityText = when (reminder.prioridad) {
        ReminderPriority.Baja -> "Baja"
        ReminderPriority.Media -> "Media"
        ReminderPriority.Alta -> "Alta"
    }

    val statusText = when (reminder.estado) {
        ReminderStatus.Pendiente -> "Pendiente"
        ReminderStatus.Completado -> "Completado"
        ReminderStatus.Cancelado -> "Cancelado"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = reminder.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            reminder.descripcion?.let { description ->
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = categoryColor,
                    modifier = Modifier.size(10.dp)
                ) {}

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = categoryInfo?.label ?: "Sin categoría",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Prioridad: $priorityText",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = when (reminder.estado) {
                    ReminderStatus.Pendiente ->
                        MaterialTheme.colorScheme.primaryContainer
                    ReminderStatus.Completado ->
                        Color(0xFFD9F2DF)
                    ReminderStatus.Cancelado ->
                        MaterialTheme.colorScheme.surfaceVariant
                }
            ) {
                Text(
                    text = statusText,
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 5.dp
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = when (reminder.estado) {
                        ReminderStatus.Pendiente ->
                            MaterialTheme.colorScheme.onPrimaryContainer
                        ReminderStatus.Completado ->
                            Color(0xFF185C2C)
                        ReminderStatus.Cancelado ->
                            MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }

            reminder.vencimiento?.let { date ->
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Vencimiento: $date",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
