package com.ilidev.xptask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilidev.xptask.mvp.model.ModelMainActivity
import com.ilidev.xptask.mvp.presenter.PresenterMainActivity
import com.ilidev.xptask.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this)
        setContentView(view.binding.root)

        presenter = PresenterMainActivity(view,ModelMainActivity(this))
        presenter.onCreate()

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}