package ru.sergeykamyshov.rostovtransport.data.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress

@Entity(tableName = "deposit_address")
class DepositAddressEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var type: Int = 1
    lateinit var desc: String
    lateinit var address: String
    lateinit var schedule: String
    lateinit var location: String

    fun toDepositAddress(): DepositAddress {
        return DepositAddress(
                type,
                desc,
                address,
                schedule,
                location
        )
    }

}

