package jp.sadashi.sample.apollo.client.infra.graphql

import com.apollographql.apollo.ApolloClient

object ApiClient {
    private const val SERVER_URL = "https://apollo-fullstack-tutorial.herokuapp.com"

    val apolloClient: ApolloClient = ApolloClient.builder()
        .serverUrl(SERVER_URL)
        .build()
}