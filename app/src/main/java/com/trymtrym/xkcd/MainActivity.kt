package com.trymtrym.xkcd

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso;
import com.trymtrym.xkcd.adapter.ComicsAdapter

class MainActivity : AppCompatActivity() {
    @Inject lateinit var factory: ComicViewModel.Factory
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ComicsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: ComicViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = (application as App).component()
        component.inject(this)

        val model = ViewModelProviders.of(this, factory).get(ComicViewModel::class.java)
        model.data().observe(this, Observer {
            viewAdapter.replaceDataset(it)
        })

        viewManager = LinearLayoutManager(this)
        viewAdapter = ComicsAdapter(arrayOf())

        recyclerView = findViewById<RecyclerView>(R.id.comicRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(false)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        viewModel = model;

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.refreshComicsButton -> {
                viewModel.requestNewComic();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
