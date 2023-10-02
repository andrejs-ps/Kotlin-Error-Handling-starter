package m4_throwing._2custom_exception

import java.io.IOException

data class Job(val id: Int)

class JobFetchException(message: String, cause: Throwable) : RuntimeException(message, cause)

class JobService {
    fun fetchJobById(jobId: String): Job {
        try {
            // API call to fetch job entity using jobId
            throw IOException("Not authorized")
        } catch (e: IOException) {
            throw JobFetchException(
                message = "Request failed due to an IO exception. Id: $jobId",
                cause = e
            )
        } catch (e: Exception) {
            throw JobFetchException(
                message = "Request failed. Request Job Id: $jobId. Message: ${e.message}}",
                cause = e
            )
        }
    }
}

fun main() {
    val jobService = JobService()

    try {
        val job = jobService.fetchJobById("50")
        // do things with Job
    } catch (e: JobFetchException) {
        println("Ooopsie, sorry! ${e.message}")
        println("${e.cause}")
    }
}