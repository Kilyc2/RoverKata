package com.example.roverkata

import android.support.design.widget.FloatingActionButton
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.example.roverkata.presenters.MainPresenter
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class MainActivityTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    private lateinit var sut: MainActivity
    @Mock
    lateinit var presenterMock: MainPresenter

    @Before
    fun setUp() {
        val serverLocator = RuntimeEnvironment.application as ServerLocator
        sut = Robolectric.setupActivity(MainActivity::class.java)
        presenterMock = serverLocator.getMainPresenter(sut)
    }

    @Test
    fun onCreateCallsViewReady() {
        verify(presenterMock).viewReady()
    }

    @Test
    fun clickButtonUpdateTextViewStatus() {
        sut.findViewById<FloatingActionButton>(R.id.fab).performClick()

        verify(presenterMock).buttonWasPressed()
    }

//    @Test
//    fun displayStatusShowStatusInTextView() {
//        val statusText = "Status text"
//        sut.displayStatus(statusText)
//
//        onView(withId(R.id.tvState)).check(matches(withText(statusText)))
//    }
}