package jp.sadashi.sample.apollo.client.domain

import com.apollographql.apollo.api.Response
import jp.sadashi.sample.apollo.client.graphql.SearchUserQuery
import jp.sadashi.sample.apollo.client.graphql.UserDetailQuery

interface UserRepository {
    suspend fun query(query: String, cursor: String?): Response<SearchUserQuery.Data>
    suspend fun get(name: String): Response<UserDetailQuery.Data>
}