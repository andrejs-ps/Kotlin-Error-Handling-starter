package m5_advanced._2sealed_classes


import java.io.IOException
import java.io.UncheckedIOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
fun main() {

    try {
        val user = getUserInfo("andrejs-ps")
        println("${user.login} with id: ${user.id}")
    } catch (e: UserNotFoundException) {
        println("Could not find the requested user")
    } catch (e: Exception) {
        println("Ooops, some network issue!")
    }

}

fun getUserInfo(userLogin: String): GitHubUser {
    val url = "https://api.github.com/users/$userLogin"
    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder().uri(URI.create(url)).build()
    try {
        val response = if(Math.random() < 0.6) httpClient.send(request, HttpResponse.BodyHandlers.ofString()) else throw IOException("Oops!")
        if (response.statusCode() != 200) {
            throw UserNotFoundException("Request failed with status code: ${response.statusCode()}")
        }
        return parseGitHubUser(response.body())

    } catch (e: IOException) {
        throw UncheckedIOException("Request failed", e)
    }
}