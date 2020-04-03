$(function(){
	var item = $(".categories");
	$.each(item, function(index, data){
		var menuId = $(this).children('li').children('a').data("menuid");
		if($("#menuId").val() == menuId){
			$(".categories > li > a").removeClass("active");
			$("[data-menuid=" + menuId + "]").addClass("active");
		}
	});
	
	
})