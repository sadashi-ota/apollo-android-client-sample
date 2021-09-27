package jp.sadashi.sample.apollo.client.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.apollographql.apollo.exception.ApolloException
import dagger.hilt.android.AndroidEntryPoint
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.FragmentUserListBinding
import jp.sadashi.sample.apollo.client.domain.UserRepository
import jp.sadashi.sample.apollo.client.graphql.SearchUserQuery
import kotlinx.coroutines.channels.Channel
import logcat.asLog
import logcat.logcat
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    internal lateinit var repository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = activity?.findNavController(R.id.nav_host_fragment) ?: run {
            logcat { "Activity is null." }
            return
        }
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        val nodes = mutableListOf<SearchUserQuery.Node?>()
        val adapter = UserListAdapter(nodes)
        binding.userListView.adapter = adapter

        val channel = Channel<Unit>(Channel.CONFLATED)

        // offer a first item to do the initial load else the list will stay empty forever
        channel.trySend(Unit)
        adapter.onEndOfListReached = {
            channel.trySend(Unit)
        }

        lifecycleScope.launchWhenResumed {
            for (item in channel) {
                val response = try {
                    repository.query(query = "sadashi", cursor = null)
                } catch (e: ApolloException) {
                    logcat { "Failure : ${e.asLog()}" }
                    return@launchWhenResumed
                }

                val search = response.data?.search ?: break

                search.nodes?.let { it ->
                    nodes.addAll(it)
                    adapter.notifyDataSetChanged()
                }

                if (!search.pageInfo.hasNextPage) {
                    break
                }
            }

            adapter.onEndOfListReached = null
            channel.close()
        }

        adapter.onItemClicked = { user ->
            findNavController().navigate(
                UserListFragmentDirections.openUserDetail(login = user.login)
            )
        }
    }
}