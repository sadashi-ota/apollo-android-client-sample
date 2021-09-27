package jp.sadashi.sample.apollo.client.infra

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.sadashi.sample.apollo.client.domain.UserRepository
import jp.sadashi.sample.apollo.client.graphql.type.CustomType
import okhttp3.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InfraModule {

    companion object {
        private const val SERVER_URL = "https://api.github.com/graphql"
        private const val ACCESS_TOKEN = ""
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator { _, response ->
                response.request().newBuilder()
                    .addHeader("Authorization", "Bearer $ACCESS_TOKEN")
                    .build()
            }.build()
    }

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .okHttpClient(okHttpClient)
            .addCustomTypeAdapter(CustomType.URI, CustomUriAdapter())
            .serverUrl(SERVER_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideLaunchDetailRepository(impl: UserRepositoryImpl): UserRepository {
        return impl
    }

}