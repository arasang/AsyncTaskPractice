package park.sangeun.aysnctest.common

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EnumValidator: ConstraintValidator<ValidEnum, Any> {
    private lateinit var enumClass: Class<out Enum<*>>

    override fun initialize(constraintAnnotation: ValidEnum) {
        this.enumClass = constraintAnnotation.enumClass.java
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true

        return enumClass.enumConstants.any { target ->
            target.name == value.toString().uppercase()
        }
    }
}