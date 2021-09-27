package jp.sadashi.sample.apollo.client.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.FragmentUserDetailsBinding
import logcat.logcat

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val args: UserDetailsFragmentArgs by navArgs()
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = activity?.findNavController(R.id.nav_host_fragment) ?: run {
            logcat { "Activity is null." }
            return
        }
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.userIcon.load(user.avatarUrl) {
                placeholder(R.drawable.ic_placeholder)
            }
            binding.userName.text = user.name
        }

        viewModel.get(login = args.login)
    }
}