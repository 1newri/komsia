var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors
	, elPlaceHolder: "boardContent"
	, sSkinURI: "/lib/smarteditor/dist/SmartEditor2Skin.html"
    , fCreator: "createSEditor2"
    , htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : false,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		fOnBeforeUnload : function(){
			//alert(\"완료!\");
		}
	} ,
	fOnAppLoad : function(){
	}
});

function submitContents(obj){
	oEditors.getById["boardContent"].exec("UPDATE_CONTENTS_FIELD", []);
}