package chernishev.rsue.golosovaniev2.retrofit.question

data class AddQuestionRequest(
    val voteid:String,
    val content: String,
    val datevote:String
)
