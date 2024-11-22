package com.example.nexus_sire_mobile.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.nexus_sire_mobile.MainActivity
import com.example.nexus_sire_mobile.R
import java.util.UUID

object NotificationUtils {

    // Verificar a permissão de notificações
    fun checkNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // No Android 13 ou superior, verificamos a permissão POST_NOTIFICATIONS
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        } else {
            true // No Android abaixo de 13, não é necessário solicitar permissão
        }
    }

    // Solicitar permissão para notificações (se necessário)
    fun requestNotificationPermission(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // No Android 13 ou superior, solicitar permissão
            if (!checkNotificationPermission(context)) {
                val intent = Intent(context, MainActivity::class.java) // Você pode definir para exibir uma Activity ou Snackbar
                val pendingIntent = PendingIntent.getActivity(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                Toast.makeText(context, "Permissão de Notificação necessária", Toast.LENGTH_SHORT).show()
                // Se necessário, pode adicionar lógica para solicitar permissão via um ActivityResultLauncher
            }
        }
    }

    // Criação do canal de notificação (necessário no Android 8.0 ou superior)
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                "Energy Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificações para consumo de energia"
            }

            val manager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    // Exibição da notificação
    fun showNotification(context: Context, title: String, message: String) {
        // Verificar a permissão antes de mostrar a notificação
        if (checkNotificationPermission(context)) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val builder = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Fecha a notificação ao clicar nela

            // Usando UUID para garantir um ID único para a notificação
            val notificationId = UUID.randomUUID().toString().hashCode()
            NotificationManagerCompat.from(context).notify(notificationId, builder.build())
        } else {
            // Caso a permissão não tenha sido concedida, solicitar a permissão
            requestNotificationPermission(context)
        }
    }
}
