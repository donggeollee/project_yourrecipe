
function deleteImg(id){
	
	$.ajax({ 
		url: "/api/image/"+id, 
		type:"delete",
		contentType: "application/json",
		dataType: "json", 
		success:function(response){
			if ( response.description == "OK" ){
				alert("이미지가 삭제 되었습니다");
				$("#article-image-"+id).remove();
			} else if ( response.description == "ERROR" ){
				alert("해당 이미지가 존재하지 않습니다")
			}
		},
		error:function(result){
			alert('이미지 삭제 과정에서 문제 발생 - 관리자 문의 요망');
		}

	});
}