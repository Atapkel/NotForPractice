
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.Genre


@Composable
fun HomeScreen(
    genres: List<Genre>,
    navController: NavHostController,
//    lazyColumnState: LazyListState
) {
    LazyColumn(
//        state = lazyColumnState,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(26.dp),

        ) {
        item {
            Spacer(Modifier.height(55.dp))
            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
            Spacer(Modifier.height(47.dp))
        }
        items(genres) { genre ->
            GenreSection(genre, navController)
        }
    }
}