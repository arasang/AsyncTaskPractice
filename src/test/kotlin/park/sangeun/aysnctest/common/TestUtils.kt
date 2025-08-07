package park.sangeun.aysnctest.common

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

fun Any.toMultiValueMap(): MultiValueMap<String, String> {
    val map = LinkedMultiValueMap<String, String>()
    this::class.memberProperties.forEach { prop ->
        @Suppress("UNCHECKED_CAST")
        val kProp = prop as KProperty1<Any, *>
        kProp.isAccessible = true
        val value = kProp.get(this)
        if (value != null) {
            map.add(kProp.name, value.toString())
        }
    }
    return map
}
