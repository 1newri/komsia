var CustomUploadAdapter = function(loader, path, fn_resolve){
	this.coonsturtor = function(loader){
		this.loader = loader;
		this.path = path;
		this.fn_resolve = fn_resolve;
	};	
	
	this.upload = function(){
		return new Promise(function (resolve, reject){
			this.xhr = ajax_file_upload({
				loader : loader
				, resolve : resolve
				, reject : reject
				, files: [loader.file]
				, path : path
				, fn_progress:function(e){
					e.lengthComputable && (loader.uploadTotal = e.total, loader.uploaded = e.loaded);
				}
				, fn_success : function(e){
					resolce(fn_resolve && fn_resolve(e));
				}
				, fn_error : function(e){
					reject("upload fail => " + `${loader.file.name}.`);
				}
				, fn_abort : reject
			});
		});
	};
	this.abort = function(){
		return this.xhr.abort && this.xhr.abort();
	}
}