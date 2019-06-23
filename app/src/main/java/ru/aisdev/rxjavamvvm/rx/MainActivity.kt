package ru.aisdev.rxjavamvvm.rx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.aisdev.rxjavamvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myGithubStarsRepos.setOnClickListener {
            startActivity(Intent(this,MyStarsRepos::class.java))
        }

    }
}
