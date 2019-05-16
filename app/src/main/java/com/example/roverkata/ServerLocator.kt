package com.example.roverkata

import com.example.roverkata.presenters.MainPresenter
import com.example.roverkata.presenters.MainView

interface ServerLocator {
    fun getMainPresenter(view: MainView): MainPresenter
}