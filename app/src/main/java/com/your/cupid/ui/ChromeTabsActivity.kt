package com.your.cupid.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.your.cupid.EXTRA_TASK_URL
import com.your.cupid.R

class ChromeTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrome_tabs)

        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this@ChromeTabsActivity, R.color.colorPrimary))
        builder.addDefaultShareMenuItem()
        builder.setShowTitle(false)
        builder.enableUrlBarHiding()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(intent.getStringExtra(EXTRA_TASK_URL)))
    }
}
