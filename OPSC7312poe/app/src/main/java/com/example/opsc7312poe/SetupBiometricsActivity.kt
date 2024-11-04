package com.example.opsc7312poe

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SetupBiometricsActivity : AppCompatActivity() {

    private lateinit var buttonGoToSettings: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setup_biometrics)

        // Set up the button to go to the settings
        buttonGoToSettings = findViewById(R.id.button_go_to_settings)

        buttonGoToSettings.setOnClickListener {
            // Direct the user to the biometric settings
            startActivity(Intent(Settings.ACTION_BIOMETRIC_ENROLL))
        }

        // Set up window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
