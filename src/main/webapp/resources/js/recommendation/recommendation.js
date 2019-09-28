
	var obj = new Object();
		obj.transactionTime = " ";
		obj.resultCode = " "
		obj.description = " ";

function btnLike(){
	if ( userId.trim().length == 0){
		alert("로그인 후 이용가능한 서비스입니다.");
		return;
	}
	
	var data = new Object();
	data.user_id = userId;
	data.article_id = articleId;
	
	obj.data = data;
	
	var request = JSON.stringify(obj);
	
	$.ajax({ 
		url: "/api/recommendation", 
		type:"post",
		contentType: "application/json",
		data: request,
		dataType: "json", 
		success:function(response){
			var recomId = response.data.id;
			var r1 = Number($("#recomCount").text());
			var recomCount = r1 + 1;
			var temp = "<button class=\"btn btn-danger btn-sm\" onclick=\"btnNoLike("+recomId+")\">추천취소"
				+ "<span id=\"recomCount\">"+recomCount+"</span></button>";
			$(".recommendation-cont").html(temp);
		},
		error:function(result){
			alert('추천 과정에서 문제 발생 - 관리자 문의 요망');
		}

	});
}

function btnNoLike(recomId){
	if ( userId.trim().length == 0){
		alert("로그인 후 이용가능한 서비스입니다.");
		return;
	}
	$.ajax({ 
		url: "/api/recommendation/"+recomId, 
		type:"delete",
		contentType: "application/json",
		dataType: "json", 
		success:function(response){
			var r1 = Number($("#recomCount").text());
			var recomCount = r1 - 1;
			var temp = "<button class=\"btn btn-default btn-sm\" onclick=\"btnLike()\">추천"
			+ "<span id=\"recomCount\">"+recomCount+"</span></button>";
			$(".recommendation-cont").html(temp);
		},
		error:function(result){
			alert('추천 과정에서 문제 발생 - 관리자 문의 요망');
		}

	});
}
	

		
