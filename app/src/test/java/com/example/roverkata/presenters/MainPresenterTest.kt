package com.example.roverkata.presenters

import com.example.roverkata.models.Rover
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness

class MainPresenterTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    private lateinit var sut: MainPresenter
    @Mock
    private lateinit var viewMock: MainView
    @Mock
    private lateinit var roverMock: Rover


    @Before
    fun setUp() {
        sut = MainPresenter(view = viewMock, roverModel = roverMock)
    }

    //<editor-fold desc="Init">
    @Test
    fun sutIsNotNull() {
        assertNotNull(sut)
    }

    //</editor-fold>

    @Test
    fun viewReadyDisablesButton() {
        sut.viewReady()

        verify(viewMock).disableButton()
    }

    @Test
    fun disableButtonWhenTextIsEmpty() {
        sut.textChanged("")

        verify(viewMock).disableButton()
    }

    @Test
    fun enableButtonWhenTextHasContent() {
        sut.textChanged("I have text!")

        verify(viewMock).enableButton()
    }

    @Test
    fun verifyExecuteRoverWasCalled() {
        sut.buttonWasPressed()

        verify(roverMock, atLeastOnce()).execute("")
    }

    @Test
    fun verifyRoverStatus() {
        whenever(roverMock.toString()).then { "Direction: N, Position: (5, 8)" }
        sut.buttonWasPressed()

        verify(viewMock).displayStatus("Direction: N, Position: (5, 8)")
    }
}