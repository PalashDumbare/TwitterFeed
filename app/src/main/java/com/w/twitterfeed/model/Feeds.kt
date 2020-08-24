import android.util.Log

class Feeds (

	val created_at : String,
	val id : Long,
	val id_str : String,
	val text : String,
	val truncated : Boolean,
	val entities : Entities,
	val source : String,
	val in_reply_to_status_id : String,
	val in_reply_to_status_id_str : String,
	val in_reply_to_user_id : String,
	val in_reply_to_user_id_str : String,
	val in_reply_to_screen_name : String,
	val user : User,
	val geo : String,
	val coordinates : String,
	val place : String,
	val contributors : String,
	val is_quote_status : Boolean,
	val retweet_count : Int,
	val favorite_count : Int,
	val favorited : Boolean,
	val retweeted : Boolean,
	val possibly_sensitive : Boolean,
	val possibly_sensitive_appealable : Boolean,
	val lang : String
){
	 fun getPost() : String{
		 var fullPost : String = "<html>"
		 val post = text.split(" ")
		 for (word in post){
 			 if (word.length > 0 && (word[0] == '@' || word[0] == '#')){
				 fullPost = fullPost+" <span style='color: #2bb1ff;'>${word}</span>"
			 }else{
				 fullPost = fullPost+" "+word
			 }
		 }

		 fullPost = fullPost+"</html>"
 		 return fullPost
 	 }
 }

