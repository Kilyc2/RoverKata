package com.example.roverkata

import android.app.Application
import com.example.roverkata.models.Rover
import com.example.roverkata.presenters.MainPresenter
import com.example.roverkata.presenters.MainView

class App : Application(), ServerLocator {

    override fun getMainPresenter(view: MainView) =
        MainPresenter(view = view, roverModel = Rover())
}
