package com.example.roverkata.presenters

import com.example.roverkata.models.Rover

class MainPresenter(private val view: MainView, private val roverModel: Rover) {

    private var commands = ""

    fun viewReady() {
        view.disableButton()
    }

    fun textChanged(text: String) {
        commands = text
        if (text.isEmpty()) {
            view.disableButton()
        } else {
            view.enableButton()
        }
    }

    fun buttonWasPressed() {
        roverModel.execute(commands = commands)
        view.displayStatus(roverModel.toString())
    }

}