package jp.sadashi.sample.apollo.client.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.LaunchDetailsFragmentBinding
import jp.sadashi.sample.apollo.client.infra.graphql.ApiClient
import jp.sadashi.sample.apollo.client.infra.graphql.LaunchDetailsQuery

class LaunchDetailsFragment : Fragment() {

    private lateinit var binding: LaunchDetailsFragmentBinding
    private val args: LaunchDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LaunchDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            binding.bookButton.visibility = View.GONE
            binding.bookProgressBar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.error.visibility = View.GONE

            val response = try {
                ApiClient.apolloClient.query(LaunchDetailsQuery(id = args.launchId)).await()
            } catch (e: ApolloException) {
                binding.progressBar.visibility = View.GONE
                binding.error.text = "Oh no... A protocol error happened"
                binding.error.visibility = View.VISIBLE
                return@launchWhenResumed
            }

            val launch = response.data?.launch
            if (launch == null || response.hasErrors()) {
                binding.progressBar.visibility = View.GONE
                binding.error.text = response.errors?.get(0)?.message
                binding.error.visibility = View.VISIBLE
                return@launchWhenResumed
            }

            binding.progressBar.visibility = View.GONE

            binding.missionPatch.load(launch.mission?.missionPatch) {
                placeholder(R.drawable.ic_placeholder)
            }
            binding.site.text = launch.site
            binding.missionName.text = launch.mission?.name
            val rocket = launch.rocket
            binding.rocketName.text = "🚀 ${rocket?.name} ${rocket?.type}"
        }
    }
}