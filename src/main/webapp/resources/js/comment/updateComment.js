
var updateCount = 0;
function updateInputComment(commentId,commnetContent){
	if ( updateCount == 1 ){
		alert("진행중인 댓글 수정을 먼저 완료해주세요");
		return;
	} else{
		updateCount++;
	}
	$("#update-content-"+commentId).html("<textarea class=\"form-control\" rows=\"4\" id=\"up-comment-result\">"+commnetContent+"</textarea>");
	var plusButton = "<button class=\"btn btn-primary btn-sm\" onclick=\"updateComment("+commentId+");\">등록</button>"
	+ "<button class=\"btn btn-primary btn-sm\" onclick=\"updateCancel();\">취소</button>"
	$("#btn-up-comment-"+commentId).html(plusButton); 
}

function updateCancel(){
	location.reload();
}

function updateComment(commentId){
	
	if ( $("#up-comment-result").val().trim().length==0){
		alert("내용을 입력하고 등록버튼을 눌러주세요"); 
		return;
	}
	
	var obj = new Object();
	obj.transactionTime = " ";
	obj.resultCode = " "
	obj.description = " ";
		
	var data = new Object();
	data.id = commentId;
	data.content = $("#up-comment-result").val();
	data.article_id = articleId;
	data.user_id = userId;
	 
	obj.data = data;
	
	var request = JSON.stringify(obj);
	
	$.ajax({ 
		url: "/api/comment", 
		type:"put",
		contentType: "application/json",
		data: request,
		dataType: "json", 
		success:function(response){
			alert("댓글 수정 완료")
			location.reload();
		},
		error:function(result){
			alert('댓글 등록 과정에서 문제 발생 - 관리자 문의 요망');
		}
	});
}



