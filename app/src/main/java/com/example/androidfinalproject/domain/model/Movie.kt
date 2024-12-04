import com.example.androidfinalproject.domain.model.Country
import com.example.androidfinalproject.domain.model.Genre

data class Movie(
    val kinopoiskId: Int = 1,
    val imdbId: String? = "",
    val nameRu: String = "",
    val nameEn: String? = "",
    val nameOriginal: String? = "",
    val countries: List<Country> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val ratingKinopoisk: Double? = 0.0,
    val ratingImdb: Double? = 0.0,
    val year: Int = 0,
    val type: String = "",
    val posterUrl: String = "",
    val posterUrlPreview: String = "",
    val filmLength: Int = 0,
    val slogan: String = "",
    val description: String = "",
    val shortDescription: String = "",
    val editorAnnotation: String = "",
    val ratingAgeLimits: String = ""
)
