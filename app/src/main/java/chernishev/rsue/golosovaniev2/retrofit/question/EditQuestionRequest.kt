package chernishev.rsue.golosovaniev2.retrofit.question

data class EditQuestionRequest(
    val id: String,
    val voteid:String,
    val content: String,
    val datevote:String
)
