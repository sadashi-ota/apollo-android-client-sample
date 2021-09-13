package jp.sadashi.sample.apollo.client.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.LaunchItemBinding
import jp.sadashi.sample.apollo.client.infra.graphql.LaunchListQuery

class LaunchListAdapter(private val launches: List<LaunchListQuery.Launch>) :
    RecyclerView.Adapter<LaunchListAdapter.ViewHolder>() {

    class ViewHolder(val binding: LaunchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LaunchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    var onEndOfListReached: (() -> Unit)? = null
    var onItemClicked: ((LaunchListQuery.Launch) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = launches[position]
        holder.binding.site.text = launch.site ?: ""
        holder.binding.missionName.text = launch.mission?.name
        holder.binding.missionPatch.load(launch.mission?.missionPatch) {
            placeholder(R.drawable.ic_placeholder)
        }

        if (position == launches.size - 1) {
            onEndOfListReached?.invoke()
        }

        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(launch)
        }
    }
}