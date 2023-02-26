package asmaa.khb.usersproject.ui.viewholders

import asmaa.khb.usersproject.R
import asmaa.khb.usersproject.databinding.ItemUserBinding
import asmaa.khb.usersproject.models.User
import com.bumptech.glide.Glide
import com.haizo.generaladapter.model.ViewHolderContract
import com.haizo.generaladapter.viewholders.BaseBindingViewHolder

val USER_VIEW_HOLDER_CONTRACT = ViewHolderContract(
    viewHolderClass = UserViewHolder::class.java, layoutResId = R.layout.item_user
)

class UserViewHolder(
    private val binding: ItemUserBinding
) : BaseBindingViewHolder<User>(binding) {

    override fun onBind(listItem: User) {
        binding.textViewFullName.let { tv ->
            tv.text = tv.context.getString(R.string.item_full_name, "${listItem.firstName} ${listItem.lastName}")

        }
        binding.textViewEmail.let { tv ->
            tv.text = tv.context.getString(R.string.item_email, listItem.emailAddress)
        }
        binding.textViewAddress.let { tv ->
            tv.text = tv.context.getString(R.string.item_address, listItem.city)
        }
        Glide.with(itemView).load(listItem.avatar).into(binding.imageViewAvatar)
    }
}