package chernishev.rsue.golosovaniev2.retrofit.question

data class ServerRespQuestion(
    val id:Long,
    val voteid:Long,
    val content: String,
    val datevote:String,
    val votetitle:String
)
