package m5_advanced._2sealed_classes

import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun main() {

    when (val result = getUserInfoAnother("kotlin")) {
        is Result.Success -> println("${result.value.login} with id: ${result.value.id}")
        is Result.Failure -> {
            println("A problem happened. Message: ${result.error}")
            println("Here's the underlying cause: ${result.cause}")
        }
    }

    when (val result = getRepoInfo("kotlin-for-java-developers")) {
        is Result.Success -> println("${result.value.fullName} with id: ${result.value.id}")
        is Result.Failure -> {
            println("A problem happened. Message: ${result.error}")
            println("Here's the underlying cause: ${result.cause}")
        }
    }

}
fun getRepoInfo(repo: String): Result<GitHubRepo> {
    val url = "https://api.github.com/repos/andrejs-ps/$repo"
    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder().uri(URI.create(url)).build()
    try {
        val response = if (Math.random() < 0.9) httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        ) else throw IOException("Oops!")
        if (response.statusCode() != 200) {
            return Result.Failure("Request failed with status code: ${response.statusCode()}")
        }
        return Result.Success(GitHubRepo(123, "abc"))

    } catch (e: IOException) {
        return Result.Failure("Request failed", e)
    }
}
fun getUserInfoAnother(userLogin: String): Result<GitHubUser> {
    val url = "https://api.github.com/users/$userLogin"
    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder().uri(URI.create(url)).build()
    try {
        val response = if (Math.random() < 0.9) httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        ) else throw IOException("Oops!")
        if (response.statusCode() != 200) {
            return Result.Failure("Request failed with status code: ${response.statusCode()}")
        }
        return Result.Success(parseGitHubUser(response.body()))

    } catch (e: IOException) {
        return Result.Failure("Request failed", e)
    }
}
// out is like <? extends T>
sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val error: String, val cause: Exception? = null) : Result<Nothing>()
}

sealed class GitHubResult<out T> {
    data class Success<out T>(val value: T) : GitHubResult<T>()
    data class Failure(val error: String, val cause: Exception? = null) : GitHubResult<Nothing>()
}