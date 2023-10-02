package m5_advanced._2sealed_classes

fun parseGitHubUser(json: String): GitHubUser {
    val login = json.substringAfter("\"login\":").substringBefore(",").trim('"', ' ')
    val id = json.substringAfter("\"id\":").substringBefore(",").trim().toInt()
    return GitHubUser(login, id)
}