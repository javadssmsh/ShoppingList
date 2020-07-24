package developer.company.shopinglistmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import developer.company.shopinglistmvvm.data.db.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: kotlin.synchronized(LOCK) {
            instance
                ?: createDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java, "shoppingDB.db"
            ).build()
    }
}