package jp.sadashi.sample.apollo.client.infra

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.sadashi.sample.apollo.client.domain.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InfraModule {

    companion object {
        private const val SERVER_URL = "https://api.github.com/graphql"
    }

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder().serverUrl(SERVER_URL).build()
    }

    @Singleton
    @Provides
    fun provideLaunchDetailRepository(impl: UserRepositoryImpl): UserRepository {
        return impl
    }

}