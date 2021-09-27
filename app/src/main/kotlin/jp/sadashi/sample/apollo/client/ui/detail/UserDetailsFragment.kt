package jp.sadashi.sample.apollo.client.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import jp.sadashi.sample.apollo.client.R
import jp.sadashi.sample.apollo.client.databinding.FragmentUserDetailsBinding

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val args: UserDetailsFragmentArgs by navArgs()

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

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.userIcon.load(user.avatarUrl) {
                placeholder(R.drawable.ic_placeholder)
            }
            binding.userName.text = user.name
        }

        viewModel.get(login = args.login)
    }
}