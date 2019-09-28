/**
 * 
 */
$(function(){
	$("#btnSave").on("click",function(){
		var title = $("#title").val().trim();
		var content = $("#content").val().trim();
		
		if ( title == ""){
			alert("제목을 입력해주세요");
			return;
		} else if( content == "" ) {
			alert("내용을 입력해주세요");
			return;
		}
		
		$("#form-cont").submit();
	});
})
 
 