package m4_throwing._1util_throw

data class Job1(val title: String, val company: String, val salary: Double) {
    init {
        if (title.isBlank()) {
            throw IllegalArgumentException("Job title must not be blank")
        }

        if (company.isBlank()) {
            throw IllegalArgumentException("Company name must not be blank")
        }

        if (salary <= 0.0) {
            throw IllegalArgumentException("Salary must be a non-negative value")
        }
    }
}

data class Job2(val title: String, val company: String, val salary: Double) {
    init {
        require(title.isNotBlank()) { "Job title must not be blank" }
        require(company.isNotBlank()) { "Company name must not be blank" }
        require(salary > 0.0) { "Salary must be a non-negative value" }
    }
}

data class Job3(val title: String, val company: String, val salary: Double?) {

    init {
        checkNotNull(salary) { "Message " }
        // or
        check(salary != null && salary >= 0.0) { "The salary must not be null and be above 0" }
    }

}

fun main() {

    val job1 = Job2("Software Engineer", "ABC Inc.", 80000.0)
    val job2 = Job2("Product Manager", "XYZ Corp.", 95000.0)


    val invalidJob = Job2("", "Invalid Company", -1000.0)
}