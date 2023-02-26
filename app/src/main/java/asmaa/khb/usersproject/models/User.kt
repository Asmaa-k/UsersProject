package asmaa.khb.usersproject.models

import androidx.room.Entity
import asmaa.khb.usersproject.ui.viewholders.USER_VIEW_HOLDER_CONTRACT
import com.haizo.generaladapter.model.ListItem
import com.haizo.generaladapter.model.ViewHolderContract

@Entity(tableName = "users", primaryKeys = ["ID"])
data class User constructor(
    val ID: Long,
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val avatar: String,
    val city: String

) : ListItem {
    override val viewHolderContract: ViewHolderContract get() = USER_VIEW_HOLDER_CONTRACT
    override fun itemUniqueIdentifier(): String = ID.toString()
    override fun areContentsTheSame(newItem: ListItem): Boolean = true
}
