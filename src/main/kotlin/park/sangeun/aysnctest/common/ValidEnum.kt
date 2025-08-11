package park.sangeun.aysnctest.common

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EnumValidator::class])
annotation class ValidEnum (
    val message: String = "Enum에 정의한 값을 사용하세요",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val enumClass: KClass<out Enum<*>>,
)