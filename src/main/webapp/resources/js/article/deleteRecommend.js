
function deleteRecommend(id){
	
	$.ajax({ 
		url: "/api/recommendation/"+id, 
		type:"delete",
		contentType: "application/json",
		dataType: "json", 
		success:function(response){
			if ( response.description == "OK" ){
				alert("해당 레시피를 나의 추천목록에서 삭제했습니다.")
				$("#lecipe-row-"+id).remove();
				if( $("tbody tr").length == 0 ){
					var temp = "<tr><th colspan=7 style=\"text-align:center\">" +
							"내가 추천한 게시글이 존재하지 않습니다. 마음에 드는 레시피를 추천해보세요</th></tr>";
					$("tbody").html(temp);
				}
					
			  	
			  	
			} else if ( response.description == "ERROR" ){
				alert("해당 레시피가 존재하지 않습니다")
			}
		},
		error:function(result){
			alert('추천 취소 과정에서 문제 발생 - 관리자 문의 요망');
		}

	});
	
}