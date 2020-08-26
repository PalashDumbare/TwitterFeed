import android.util.Log

class Feeds (

	val text : String,

	val user : User

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

