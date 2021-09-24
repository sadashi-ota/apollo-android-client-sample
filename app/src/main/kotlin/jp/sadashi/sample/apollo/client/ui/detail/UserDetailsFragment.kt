package jp.sadashi.sample.apollo.client.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.LaunchDetailsFragmentBinding

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: LaunchDetailsFragmentBinding
    private val args: LaunchDetailsFragmentArgs by navArgs()

    private val viewModel: UserDetailsViewModel by viewModels()

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

        viewModel.user.observe(viewLifecycleOwner) { data ->
            binding.bookButton.visibility = View.GONE
            binding.bookProgressBar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.error.visibility = View.GONE

            val launch = data.launch ?: return@observe

            binding.progressBar.visibility = View.GONE

            binding.missionPatch.load(launch.mission?.missionPatch) {
                placeholder(R.drawable.ic_placeholder)
            }
            binding.site.text = launch.site
            binding.missionName.text = launch.mission?.name
            val rocket = launch.rocket
            binding.rocketName.text = "🚀 ${rocket?.name} ${rocket?.type}"
        }

        viewModel.get(name = args.name)
    }
}