package jp.sadashi.sample.apollo.client.infra.graphql

import com.apollographql.apollo.ApolloClient

object ApiClient {
    val apolloClient: ApolloClient = ApolloClient.builder()
        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com")
        .build()
}