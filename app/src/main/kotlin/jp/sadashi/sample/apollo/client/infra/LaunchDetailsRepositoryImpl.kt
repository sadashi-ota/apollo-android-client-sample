package jp.sadashi.sample.apollo.client.infra

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import jp.sadashi.sample.apollo.client.domain.LaunchDetailsRepository
import jp.sadashi.sample.apollo.client.infra.graphql.ApiClient
import jp.sadashi.sample.apollo.client.infra.graphql.LaunchDetailsQuery

class LaunchDetailsRepositoryImpl() : LaunchDetailsRepository {
    override suspend fun query(id: String): Response<LaunchDetailsQuery.Data> {
        return ApiClient.apolloClient.query(LaunchDetailsQuery(id = id)).await()
    }

}