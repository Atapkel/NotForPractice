import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import com.example.androidfinalproject.domain.model.FilmS
import com.example.androidfinalproject.util.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce


class FilteredMoviesViewModel : ViewModel() {
    private val _movieState = MutableStateFlow<FilteredMoviesState>(FilteredMoviesState.Initial)
    val movieState: StateFlow<FilteredMoviesState> get() = _movieState
    private val repository = Repository()


    fun getFilteredMovies(
        country: Int,
        genre: Int,
        order: String,
        type: String,
        ratingFrom: Double,
        ratingTo: Double,
        yearFrom: Int,
        yearTo: Int,
        keyword: String,
        page: Int = 1
    ) {
        Log.d("filtered_search","Parameters: country=$country, genre=$genre, order=$order, type=$type, " +
                "ratingFrom=$ratingFrom, ratingTo=$ratingTo, yearFrom=$yearFrom, yearTo=$yearTo, keyword=$keyword")

        if (keyword.isBlank()) {
            _movieState.value = FilteredMoviesState.Error("Invalid input parameters.")
            return
        }
        viewModelScope.launch {
            _movieState.value = FilteredMoviesState.Loading
            try {
                val response = repository.getFilteredMovies(
                    country = country,
                    genre = genre,
                    order = order,
                    type = type,
                    ratingFrom = ratingFrom,
                    ratingTo = ratingTo,
                    yearFrom = yearFrom,
                    yearTo = yearTo,
                    keyword = keyword,
                    page = page
                )
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse?.items?.isNotEmpty() == true) {
                        _movieState.value = FilteredMoviesState.Success(movieResponse.items)
                    } else {
                        _movieState.value = FilteredMoviesState.Error("No movies found.")
                    }
                } else {
                    _movieState.value = FilteredMoviesState.Error("API Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _movieState.value = FilteredMoviesState.Error("Exception: ${e.message}")
            }
        }
    }
}

sealed class FilteredMoviesState {
    object Initial : FilteredMoviesState()
    object Loading : FilteredMoviesState()
    data class Success(val movies: List<Movie>) : FilteredMoviesState()
    data class Error(val message: String) : FilteredMoviesState()
}
