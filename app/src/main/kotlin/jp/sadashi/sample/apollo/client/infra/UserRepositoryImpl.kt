package jp.sadashi.sample.apollo.client.infra

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import jp.sadashi.sample.apollo.client.domain.UserRepository
import jp.sadashi.sample.apollo.client.graphql.SearchUserQuery
import jp.sadashi.sample.apollo.client.graphql.UserDetailQuery
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiClient: ApolloClient
) : UserRepository {
    override suspend fun query(query: String, cursor: String?): Response<SearchUserQuery.Data> {
        return apiClient.query(SearchUserQuery(query = query, cursor = Input.optional(cursor)))
            .await()
    }

    override suspend fun get(login: String): Response<UserDetailQuery.Data> {
        return apiClient.query(UserDetailQuery(login = login)).await()
    }
}