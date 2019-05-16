package com.example.roverkata.presenters

interface MainView {

    fun disableButton()

    fun enableButton()

    fun displayStatus(status: String)
}