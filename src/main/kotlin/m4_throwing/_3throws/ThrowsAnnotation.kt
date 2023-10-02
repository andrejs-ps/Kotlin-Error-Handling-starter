package m4_throwing._3throws

import java.io.IOException

data class Job(val id: Int)

class JobService {
    @Throws(IOException::class)
    fun fetchJobById(jobId: String): Job {
        // API call
        throw IOException("Not authorized")

    }
}

fun main() {

    JobService().fetchJobById("123")
}
