package chernishev.rsue.golosovaniev2.retrofit.user

data class ServerRespUser (
        val id: Long,
        val fio: String,
        val password: String,
        val phone: String,
        val email: String,
        val status: String
        )