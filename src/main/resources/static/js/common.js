
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

var entityMap = {
	    '&': '&amp;',
	    '<': '&lt;',
	    '>': '&gt;',
	    '"': '&quot;',
	    "'": '&#39;',
	    '/': '&#x2F;',
	    '`': '&#x60;',
	    '=': '&#x3D;'
	  };

function escapeHtml (string) {
    return String(string).replace(/[&<>"'`=\/]/g, function fromEntityMap (s) {
      return entityMap[s];
    });
}

function checkPasswrod(password) {
	var check1 = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/.test(password);
	
	if(!check1){
		alert("사용할 수 없는 비밀번호 조합입니다.\n패스워드 설정안내를 확인해 주세요.");
		return false;
	}
	return true;
}

function fn_inputPhoneNumber(obj) {

    var number = obj.val().replace(/[^0-9]/g, "");
    var phone = "";

    if(number.length < 4) {
        return number;
    } else if(number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if(number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
    }
    
    obj.val(phone);
}


function fn_selectBirth(){
	
	var toDay = new Date();
	var year  = toDay.getFullYear();
	var month = (toDay.getMonth()+1);
	var day   = toDay.getDate();
	
	if (month < 10) {
		month = "0" + new String(month);
	} else {
		month = new String(month);
	}
	
	if (day < 10) {
		day = "0" + new String(day);
	} else {
		day = new String(day);
	} 
	
	var birthday = year + month + day;

	var str = "";
	for (var i=year; i>=1900; i--) {
		// 년도 설정
		if (birthday.substr(0,4) == i) {
			str += "<option value='" + i + "' selected='selected'>" + i + "</option>";
		} else {
			str += "<option value='" + i + "' >" + i + "</option>";
		}
	}

	$("#selectYear").append(str);

	// 월, 일 설정
	for (var i=1; i<=31; i++) {
		var val = "";
		if (i < 10) {
			val = "0" + new String(i);
		} else {
			val = new String(i);
		} 

		if (birthday.substr(6,2) == i) {
			$("<option value='" + val + "' selected>" + val + "</option>").appendTo("#selectDay");
		} else {
			$("<option value='" + val + "'>" + val + "</option>").appendTo("#selectDay");
		}

		if (i < 13) {
			if (birthday.substr(4,2) == i) {
				$("<option value='" + val + "' selected>" + val + "</option>").appendTo("#selectMonth");
			} else {
				$("<option value='" + val + "'>" + val + "</option>").appendTo("#selectMonth");
			}
		}
	}

	// null 일경우 오늘 날짜
	if (birthday == "null") {
		$("#birthdayYear").val(year);
		$("#birthdayMonth").val(month);
		$("#birthdayDay").val(day);
	}

}
