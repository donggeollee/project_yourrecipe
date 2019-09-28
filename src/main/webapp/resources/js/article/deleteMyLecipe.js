
function deleteMyArticle(id){
	
	$.ajax({ 
		url: "/api/article/"+id, 
		type:"delete",
		contentType: "application/json",
		dataType: "json", 
		success:function(response){
			if ( response.description == "OK" ){
				alert("레시피가 삭제 되었습니다");
				$("#lecipe-row-"+id).remove();
				if( $("tbody tr").length == 0 ){
					var temp = "<tr><th colspan=7 style=\"text-align:center\">" +
							"내가 작성한 게시글이 존재하지 않습니다. 레시피를 등록해보세요</th></tr>";
					$("tbody").html(temp);
				}
			} else if ( response.description == "ERROR" ){
				alert("해당 레시피가 존재하지 않습니다")
			}
		},
		error:function(result){
			alert('이미지 삭제 과정에서 문제 발생 - 관리자 문의 요망');
		}

	});
}