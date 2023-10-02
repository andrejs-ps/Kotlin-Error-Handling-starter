package m5_advanced._2sealed_classes

import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun main() {

    // version 1
    val result: UserResult = getUserInfoNew("andrejs-ps")

    // must be exhaustive - compiler knows this because we use sealed classes
    when (result) {
        is UserResult.Success -> println("${result.user.login} with id: ${result.user.id}")
        is UserResult.Failure -> println("A problem happened. Message: ${result.error}")
    }

    // version 2
    when (val result2 = getUserInfoNew("kotlin")) {
        is UserResult.Success -> println("${result2.user.login} with id: ${result2.user.id}")
        is UserResult.Failure -> {
            println("A problem happened. Message: ${result2.error}")
            println("Here's the underlying cause: ${result2.cause}")
        }
    }

}

// result: the exception is captured into a Success/Failure container early one
fun getUserInfoNew(userLogin: String): UserResult {
    val url = "https://api.github.com/users/$userLogin"
    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder().uri(URI.create(url)).build()
    try {
        val response = if (Math.random() < 0.6) httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        ) else throw IOException("Oops!")
        if (response.statusCode() != 200) {
            return UserResult.Failure("Request failed with status code: ${response.statusCode()}")
        }
        return UserResult.Success(parseGitHubUser(response.body()))

    } catch (e: IOException) {
        return UserResult.Failure("Request failed", e)
    }
}

sealed class UserResult {
    data class Success(val user: GitHubUser) : UserResult()
    data class Failure(val error: String, val cause: Exception? = null) : UserResult()
}

