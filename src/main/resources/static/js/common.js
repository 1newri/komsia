
var lang_kor = {
		"info" : "_START_-_END_ (총_TOTAL_ 건)"
		, "lengthMenu" : "_MENU_ 개씩 보기"
		, "infoEmpty" : "0건"
		, "emptyTable" : "데이터가 없습니다."
		, "zeroRecords" : "데이터가 없습니다."
		, "paginate" : {
			"first" : "첫 페이지"
			, "last" : "마지막 페이지"
			, "next" : "다음"
			, "previous" :  "이전"
		}
};

function init_numberOnly() {
	$(".numberOnly").on("keyup", function(){
		$(this).val($(this).val().replace(/[^0-9]/g,''));
	});
}

function isEmptyParam(value){
	if(value == null || value == undefined){
		return true;
	}
	value = value.replace(/(^\s*)|(\s*$)/g,"");
	if(value == "" || value == "null" || value.length == 0){
		return true;
	}else{
		return false;
	}
}

