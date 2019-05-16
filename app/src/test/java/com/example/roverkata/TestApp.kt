package com.example.roverkata

import android.app.Application
import com.example.roverkata.presenters.MainPresenter
import com.example.roverkata.presenters.MainView
import com.nhaarman.mockitokotlin2.mock

class TestApp : Application(), ServerLocator {

    val presenter: MainPresenter = mock()

    override fun getMainPresenter(view: MainView): MainPresenter =
        presenter
}