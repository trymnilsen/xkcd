package com.trymtrym.xkcd

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso;

class MainActivity : AppCompatActivity() {
    @Inject lateinit var factory: ComicViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = (application as App).component()
        component.inject(this)

        val model = ViewModelProviders.of(this, factory).get(ComicViewModel::class.java)
        model.data().observe(this, Observer {
            Picasso.get()
                .load(it)
                .into(imageView);
        })

        val clickListener = { _: View ->
            model.requestNewComic();
        };

        button.setOnClickListener(clickListener);

    }
}
