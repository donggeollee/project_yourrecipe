$(function(){
	$("#btn-search").on("click",function(){
		var searchKeyword = $("#searchRecipe").val().trim();
		if ( searchKeyword == "" ){
			alert("검색어를 입력하세요.")
			return;
		}
		window.location.href="/1/"+searchKeyword;
	}) 
	$('#searchRecipe').keypress(function (e) {
		 var key = e.which;
		 if(key == 13) 
		  {
		    $('#btn-search').click();
		    return false;  
		  }
	});   
})