package ru.sergeykamyshov.rostovtransport.data.help

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sergeykamyshov.rostovtransport.domain.help.Contact

@Entity(tableName = "help_contact")
class ContactEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    lateinit var type: String
    lateinit var name: String
    lateinit var desc: String
    lateinit var phones: List<String>
    lateinit var address: String
    lateinit var site: String
    lateinit var emails: List<String>

    fun toContact(): Contact {
        return Contact(
                name,
                desc,
                phones,
                address,
                site,
                emails
        )
    }

}

