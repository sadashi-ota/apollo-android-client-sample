package jp.sadashi.sample.apollo.client.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.sadashi.sample.apollo.client.domain.UserRepository
import jp.sadashi.sample.apollo.client.graphql.SearchUserQuery
import jp.sadashi.sample.apollo.client.graphql.UserDetailQuery
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val user = MutableLiveData<UserDetailQuery.User>()

    fun get(login: String) {
        viewModelScope.launch {
            val response = userRepository.get(login)
            response.data?.user?.let { user.value = it }
        }
    }
}