package com.example.roverkata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.roverkata.presenters.MainPresenter
import com.example.roverkata.presenters.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by lazy {
        (application as ServerLocator).getMainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            presenter.buttonWasPressed()
        }
        presenter.viewReady()
    }

    override fun disableButton() {
    }

    override fun enableButton() {
    }

    override fun displayStatus(status: String) {

    }
}
