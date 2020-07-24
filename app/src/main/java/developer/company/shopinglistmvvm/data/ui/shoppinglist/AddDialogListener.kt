package developer.company.shopinglistmvvm.data.ui.shoppinglist

import developer.company.shopinglistmvvm.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}