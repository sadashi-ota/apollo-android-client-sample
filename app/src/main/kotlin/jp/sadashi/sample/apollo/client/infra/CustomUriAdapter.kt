package jp.sadashi.sample.apollo.client.infra

import com.apollographql.apollo.api.CustomTypeAdapter
import com.apollographql.apollo.api.CustomTypeValue

class CustomUriAdapter: CustomTypeAdapter<String> {
    override fun decode(value: CustomTypeValue<*>): String {
        return value.value.toString()
    }

    override fun encode(value: String): CustomTypeValue<*> {
        return CustomTypeValue.GraphQLString(value)
    }
}