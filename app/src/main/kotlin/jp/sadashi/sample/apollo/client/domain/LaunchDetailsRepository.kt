package jp.sadashi.sample.apollo.client.domain

import com.apollographql.apollo.api.Response
import jp.sadashi.sample.apollo.client.infra.graphql.LaunchDetailsQuery

interface LaunchDetailsRepository {
    suspend fun query(id: String): Response<LaunchDetailsQuery.Data>
}