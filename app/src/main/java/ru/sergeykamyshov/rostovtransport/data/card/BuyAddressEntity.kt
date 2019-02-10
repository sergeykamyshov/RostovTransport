package ru.sergeykamyshov.rostovtransport.data.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress

@Entity(tableName = "card_buy_address")
class BuyAddressEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var type: Int = 1
    lateinit var desc: String
    lateinit var note: String
    lateinit var locations: List<String>

    fun toBuyAddress(): BuyAddress {
        return BuyAddress(
                type,
                desc,
                note,
                locations
        )
    }

}

