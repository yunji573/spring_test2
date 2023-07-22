 
	function startRegistration(){
        let formData = {username: $("#username").val(),
        				password: $("#password").val(),
        				nickname: $("#nickname").val()
        				};
    
		$.ajax({
			type:"POST",
			url: "/user/validate",
			data: formData,
			
			success: (resp) => {
				console.log(resp); 
				let obj = JSON.parse(resp);
				if(obj.result==='success'){
					Swal.fire({
						title: obj.message,
						icon: 'success'
					}).then(()=>{
						location.href="/";
					})
				}else if (obj.result === 'fail'){
					Swal.fire({
						title: obj.message,
						icon:'error'
					})
				}
			 
				
			}
			
		});
		
	}
 