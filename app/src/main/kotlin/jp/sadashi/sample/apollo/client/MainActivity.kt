package jp.sadashi.sample.apollo.client

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.coroutines.await
import com.example.rocketreserver.LaunchListQuery
import jp.sadashi.sample.apollo.client.infra.graphql.ApiClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenResumed {
            val response = ApiClient.apolloClient.query(LaunchListQuery()).await()

            Log.d("LaunchList", "Success ${response.data}")
        }
    }
}