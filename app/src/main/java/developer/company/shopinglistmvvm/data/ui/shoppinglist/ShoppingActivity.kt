package developer.company.shopinglistmvvm.data.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import developer.company.shopinglistmvvm.R
import developer.company.shopinglistmvvm.data.db.ShoppingDatabase
import developer.company.shopinglistmvvm.data.db.entities.ShoppingItem
import developer.company.shopinglistmvvm.data.db.repositories.ShoppingRepository
import developer.company.shopinglistmvvm.data.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),viewModel)

        rv_shopping_items.layoutManager = LinearLayoutManager(this)
        rv_shopping_items.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab_add_item.setOnClickListener {
            AddShoppingItemDialog(this,
            object:AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}
