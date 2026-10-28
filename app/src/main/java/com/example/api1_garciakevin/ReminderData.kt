package com.example.api1_garciakevin
data class CategoryInfo(
    val label: String,
    val colorHex: String
)

// Lista de recordatorios simulados
val sampleReminders: List<Reminder> = listOf(
    Reminder(
        id = 1,
        titulo = "Estudiar Modulo 2",
        descripcion = "repasar unidad 1 y 2",
        categoria = ReminderCategory.Estudio,
        prioridad = ReminderPriority.Alta,
        estado = ReminderStatus.Pendiente,
        vencimiento = "2026-11-02"
    ),
    Reminder(
        id = 2,
        titulo = "Entregar Api 1",
        categoria = ReminderCategory.Estudio,
        prioridad = ReminderPriority.Alta,
        estado = ReminderStatus.Pendiente,
        vencimiento = "2026-10-28"
    ),
    Reminder(
        id = 3,
        titulo = "Ordenar el escritorio",
        categoria = ReminderCategory.Personal,
        prioridad = ReminderPriority.Baja,
        estado = ReminderStatus.Completado
    ),
    Reminder(
        id = 4,
        titulo = "Revisar modulo 3",
        descripcion = "antes de la proxima clase",
        categoria = ReminderCategory.Estudio,
        prioridad = ReminderPriority.Media,
        estado = ReminderStatus.Pendiente
    ),
    Reminder(
        id = 5,
        titulo = "Dejar el Lol",
        categoria = ReminderCategory.Salud,
        prioridad = ReminderPriority.Media,
        estado = ReminderStatus.Cancelado
    )
)

// Set: categorías sin repeticiones
val reminderCategories: Set<ReminderCategory> =
    sampleReminders.map { it.categoria }.toSet()

// Map: categoría asociada a su etiqueta y color
val categoryInfoMap: Map<ReminderCategory, CategoryInfo> = mapOf(
    ReminderCategory.Estudio to CategoryInfo("Estudio", "#3F51B5"),
    ReminderCategory.Trabajo to CategoryInfo("Trabajo", "#008577"),
    ReminderCategory.Personal to CategoryInfo("Personal", "#9C27B0"),
    ReminderCategory.Salud to CategoryInfo("Salud", "#E67E22")
)

// Filtrar recordatorios por estado
fun filterByStatus(
    status: ReminderStatus
): List<Reminder> {
    return sampleReminders.filter { it.estado == status }
}

// Filtrar recordatorios por prioridad
fun filterByPriority(
    priority: ReminderPriority
): List<Reminder> {
    return sampleReminders.filter { it.prioridad == priority }
}
