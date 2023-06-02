package chernishev.rsue.golosovaniev2.retrofit.question

data class Question(
    val id:Long,
    val voteid:Long,
    val content: String,
    val dateVote:String,
    val votetitle:String
)
