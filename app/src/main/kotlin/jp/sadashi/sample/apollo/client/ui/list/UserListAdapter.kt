package jp.sadashi.sample.apollo.client.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.ItemUserBinding
import jp.sadashi.sample.apollo.client.graphql.SearchUserQuery.Node
import jp.sadashi.sample.apollo.client.graphql.SearchUserQuery.AsUser as User

class UserListAdapter(private val nodes: List<Node?>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    var onEndOfListReached: (() -> Unit)? = null
    var onItemClicked: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = nodes[position]?.asUser ?: return
        holder.bind(user)

        if (position == nodes.size - 1) {
            onEndOfListReached?.invoke()
        }
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.icon.load(user.avatarUrl) {
                placeholder(R.drawable.ic_placeholder)
            }
            binding.name.text = user.name
            binding.itemLayout.setOnClickListener {
                onItemClicked?.invoke(user)
            }
        }
    }
}