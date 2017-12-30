package com.example.gooner10.androiddeveloperfundamentals

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.sdk25.coroutines.onClick

class AnkoActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_anko)

        verticalLayout {
            val name = editText()
            val toastButton = button("Say Hello") {
                onClick {
                    toast("Hello, ${name.text}!")
                    info("This is info message")
                    debug { "This is debug message" }
                    warn { "This is warning" }
                }
            }

            val buttonAlert = button("Show Alert") {
                onClick {
                    alert(Appcompat, "Hi, this is Anko Alert button", "Have you tried turning it off and on again?") {
                        yesButton { toast("Clicked Yes!") }
                        noButton { toast("Clicked No") }
                    }.show()
                }
            }

        }

    }
}
