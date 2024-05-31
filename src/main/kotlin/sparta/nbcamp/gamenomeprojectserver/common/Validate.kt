package sparta.nbcamp.gamenomeprojectserver.common


import sparta.nbcamp.gamenomeprojectserver.common.annotation.FloatRange
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

fun validate(obj: Any) {
    val kClass = obj::class
    for (property in kClass.memberProperties) {
        val floatRangeAnnotation = property.findAnnotation<FloatRange>()
        if (floatRangeAnnotation != null) {
            val value = property.call(obj) as? Float
            if (value != null) {
                if (value < floatRangeAnnotation.min || value > floatRangeAnnotation.max) {
                    throw IllegalArgumentException("${floatRangeAnnotation.min} 점 부터 ${floatRangeAnnotation.max} 점 사이만 평가가 가능 합니다")
                }
            }
        }
    }
}

class Validate {
}