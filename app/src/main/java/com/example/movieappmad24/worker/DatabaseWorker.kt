import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.content.Context
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.getMovies

private const val TAG = "DatabaseWorker"
class DatabaseWorker (ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    private val dao = MovieDatabase.getDatabase(context = ctx).movieDao()
    private val repository = MovieRepository(movieDao = dao)
    override suspend fun doWork(): Result {
        return try {
            var movieDbID: Long = 1
            val movies = getMovies()
            movies.forEach{
                repository.addMovie(it)
                it.images.forEach{url ->
                    repository.addMovieImages(MovieImage(movieDbId = movieDbID, url = url))
                }
                movieDbID += 1
            }
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}