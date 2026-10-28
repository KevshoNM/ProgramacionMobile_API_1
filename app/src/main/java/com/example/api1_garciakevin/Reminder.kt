package com.example.api1_garciakevin

// Categorías disponibles para los recordatorios
sealed class ReminderCategory {
    object Estudio : ReminderCategory()
    object Trabajo : ReminderCategory()
    object Personal : ReminderCategory()
    object Salud : ReminderCategory()
}

// Prioridades posibles
sealed class ReminderPriority {
    object Baja : ReminderPriority()
    object Media : ReminderPriority()
    object Alta : ReminderPriority()
}

// Estados posibles
sealed class ReminderStatus {
    object Pendiente : ReminderStatus()
    object Completado : ReminderStatus()
    object Cancelado : ReminderStatus()
}

// Modelo principal del recordatorio
data class Reminder(
    val id: Int,
    val titulo: String,
    val descripcion: String? = null,
    val categoria: ReminderCategory,
    val prioridad: ReminderPriority,
    val estado: ReminderStatus,
    val vencimiento: String? = null,
    val location: String? = null,
    val notificationTime: String? = null
)
