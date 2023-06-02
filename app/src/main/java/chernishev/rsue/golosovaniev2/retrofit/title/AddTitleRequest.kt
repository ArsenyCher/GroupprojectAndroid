package chernishev.rsue.golosovaniev2.retrofit.title

data class AddTitleRequest(
    val title:String,
    val datestart: String,
    val datefinish:String,
    val status:String
)
