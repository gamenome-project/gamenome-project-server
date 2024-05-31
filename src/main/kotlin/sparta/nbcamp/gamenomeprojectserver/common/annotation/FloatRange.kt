package sparta.nbcamp.gamenomeprojectserver.common.annotation

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class FloatRange(val min: Float = 0f, val max: Float = 5f)
