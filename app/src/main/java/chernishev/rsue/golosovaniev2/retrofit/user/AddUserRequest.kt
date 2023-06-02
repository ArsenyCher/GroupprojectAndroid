package chernishev.rsue.golosovaniev2.retrofit.user

data class AddUserRequest(
    val fio:String,
    val password: String,
    val phone:String,
    val email:String
)
