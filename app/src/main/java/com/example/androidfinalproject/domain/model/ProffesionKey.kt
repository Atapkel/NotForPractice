enum class ProfessionKey {
    WRITER,
    OPERATOR,
    EDITOR,
    COMPOSER,
    PRODUCER_USSR,
    TRANSLATOR,
    DIRECTOR,
    DESIGN,
    PRODUCER,
    ACTOR,
    VOICE_DIRECTOR,
    UNKNOWN
}

fun ConvertProfessionKeyToDescription(key: ProfessionKey): String {
    return when (key) {
        ProfessionKey.ACTOR -> "Актер"
        ProfessionKey.WRITER -> "Сценарист"
        ProfessionKey.OPERATOR -> "Оператор"
        ProfessionKey.EDITOR -> "Монтажер"
        ProfessionKey.COMPOSER -> "Композитор"
        ProfessionKey.PRODUCER_USSR -> "Продюсер СССР"
        ProfessionKey.TRANSLATOR -> "Переводчик"
        ProfessionKey.DIRECTOR -> "Режиссер"
        ProfessionKey.DESIGN -> "Дизайнер"
        ProfessionKey.PRODUCER -> "Продюсер"
        ProfessionKey.VOICE_DIRECTOR -> "Режиссер озвучки"
        ProfessionKey.UNKNOWN -> "Другое"
    }
}

fun getProfessionKeyFromString(type: String): ProfessionKey {
    return when (type.uppercase()) {
        ProfessionKey.WRITER.name -> ProfessionKey.WRITER
        ProfessionKey.OPERATOR.name -> ProfessionKey.OPERATOR
        ProfessionKey.EDITOR.name -> ProfessionKey.EDITOR
        ProfessionKey.COMPOSER.name -> ProfessionKey.COMPOSER
        ProfessionKey.PRODUCER_USSR.name -> ProfessionKey.PRODUCER_USSR
        ProfessionKey.TRANSLATOR.name -> ProfessionKey.TRANSLATOR
        ProfessionKey.DIRECTOR.name -> ProfessionKey.DIRECTOR
        ProfessionKey.DESIGN.name -> ProfessionKey.DESIGN
        ProfessionKey.PRODUCER.name -> ProfessionKey.PRODUCER
        ProfessionKey.ACTOR.name -> ProfessionKey.ACTOR
        ProfessionKey.VOICE_DIRECTOR.name -> ProfessionKey.VOICE_DIRECTOR
        else -> ProfessionKey.UNKNOWN
    }
}