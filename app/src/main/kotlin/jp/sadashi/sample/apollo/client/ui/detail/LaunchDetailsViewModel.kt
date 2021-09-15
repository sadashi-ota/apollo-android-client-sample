package jp.sadashi.sample.apollo.client.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.sadashi.sample.apollo.client.domain.LaunchDetailsRepository
import jp.sadashi.sample.apollo.client.infra.graphql.LaunchDetailsQuery
import kotlinx.coroutines.launch

class LaunchDetailsViewModel(
    private val repository: LaunchDetailsRepository
) : ViewModel() {

    val data = MutableLiveData<LaunchDetailsQuery.Data>()

    fun query(id: String) {
        viewModelScope.launch {
            val response = repository.query(id)
            response.data?.let { data.value = it }
        }
    }
}