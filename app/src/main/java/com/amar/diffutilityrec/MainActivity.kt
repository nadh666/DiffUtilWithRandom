package com.amar.diffutilityrec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ItemArrayAdapter.ListItemClickListener {
//    private lateinit var rv_list: RecyclerView
//    private lateinit var btn_refresh: Button
    private val itemList = ArrayList<ListItem>()
    private lateinit var itemArrayAdapter : ItemArrayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemArrayAdapter = ItemArrayAdapter(this)
        //Initialized Recyclerview
        val rv_list = findViewById<RecyclerView>(R.id.rv_list)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = DefaultItemAnimator()
        rv_list.adapter = itemArrayAdapter
        (0..99).mapTo(itemList) { ListItem("Item " + it) }
        itemArrayAdapter.submitList(itemList)
        //Initialized Button
        val btn_refresh = findViewById<Button>(R.id.btn_refresh)
        btn_refresh.setOnClickListener {
            val newItemList = ArrayList<ListItem>()
            (0..Random.nextInt(100)).mapTo(newItemList) { ListItem("Item $it") }
            itemArrayAdapter.submitList(newItemList)
        }
    }

    override fun onItemClick(listItem: ListItem, position: Int) {
        itemList[position].clicks++

        val newItemList = ArrayList<ListItem>()
        itemList.forEach {
            newItemList.add(ListItem(it.text, it.clicks))
        }
        itemArrayAdapter.submitList(newItemList)
    }
}