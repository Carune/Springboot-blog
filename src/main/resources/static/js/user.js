let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{
				this.save();
			});
			$("#btn-update").on("click", ()=>{
				this.update();
			});
		},
		
		save: function(){
			//alert('user의 save함수 호출됨');
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val(),
			};
			
			//console.log(data);
			
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
			// ajax 호출시 default는 비동기 호출
			$.ajax({
				type: "POST",
				url: "/auth/joinProc",
				data: JSON.stringify(data), // http body 데이터
				contentType: "application/json; charset=utf-8", // body 데이터의 타입(MIME)
				dataType: "json" // 서버에 요청하여 응답이 왔을 때(기본 문자열) json으로 온다면 => javascript 오브젝트로 변환
			}).done(function(resp){
				if(resp.status === 500){
					alert("회원가입에 실패했습니다.");
				}else{
					alert("회원가입이 완료되었습니다.");
					//console.log(resp);
					location.href = "/";
				}
				
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
	},
	update: function(){
			
			let data = {
				id: $("#id").val(),
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val(),
			};
			
			$.ajax({
				type: "PUT",
				url: "/user",
				data: JSON.stringify(data), // http body 데이터
				contentType: "application/json; charset=utf-8", // body 데이터의 타입(MIME)
				dataType: "json" // 서버에 요청하여 응답이 왔을 때(기본 문자열) json으로 온다면 => javascript 오브젝트로 변환
			}).done(function(resp){
				alert("회원수정이 완료되었습니다.");
				//console.log(resp);
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
	},
}

index.init();