package m5_advanced._1option

data class GitHubUser(var name: String, val id: Int)
fun getUser(): GitHubUser? = null
fun main() {

    val gu: GitHubUser? = getUser()

    // won't even let me run the code without a safe call
    val id = gu?.let {
        println("Hello ${gu.name}")
        gu.id
    }

    println(id ?: "no ID found")


    gu?.apply {
        this.name = "new login name"
    } ?: run {
        val substituteUser = GitHubUser("Maria", 456)
        println(substituteUser)
    }

    // the above is like:
    if (gu != null) {
        gu.name = "new login name"
    } else {
        val substituteUser = GitHubUser("Maria", 456)
        println(substituteUser)
    }

}


