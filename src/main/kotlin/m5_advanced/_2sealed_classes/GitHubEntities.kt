package m5_advanced._2sealed_classes

data class GitHubUser(val login: String, val id: Int)

data class GitHubRepo(val id: Int, val fullName: String)
