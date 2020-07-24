package developer.company.shopinglistmvvm.data.db.repositories

import developer.company.shopinglistmvvm.data.db.ShoppingDatabase
import developer.company.shopinglistmvvm.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.shoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.shoppingDao().delete(item)

    fun getAllShoppingItem() = db.shoppingDao().getAllShoppingItem()
}