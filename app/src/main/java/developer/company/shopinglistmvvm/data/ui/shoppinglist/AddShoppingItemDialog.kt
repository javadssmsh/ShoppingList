package developer.company.shopinglistmvvm.data.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import developer.company.shopinglistmvvm.R
import developer.company.shopinglistmvvm.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context,var addDialogListener: AddDialogListener):AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tv_add.setOnClickListener {
            val name = edt_name.text.toString()
            val amount = edt_mount.text.toString()

            if (name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context,"Please enter all information ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name,amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tv_cancel.setOnClickListener {
            cancel()
        }
    }
}