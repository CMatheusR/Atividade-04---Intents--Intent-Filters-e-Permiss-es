package com.example.recebecompartilhamento

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent) // Handle text being sent
                } else if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Handle single image being sent
                }
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }
    }
    private fun handleSendText(intent: Intent) {
        intent.getStringArrayExtra(Intent.EXTRA_TEXT)?.let {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (sharedText != null)
                textView2.text = sharedText
        }
    }

    private fun handleSendImage(intent: Intent) {

        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            imageView2.setImageURI(intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)
        }
    }
}
