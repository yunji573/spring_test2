/**
 * 
 */

 
let selectedRole = '';

function updateSelectedRole(select) {
  selectedRole = select.value;
}
	/*  ROLE수정 */
	function roleChange(id){
		
 
		const url = "/user/admin/update/"+id;
		console.log(url);
        let formData = {
        				id: id,
        				role: selectedRole,
        		
        				};
    
        
        console.log(formData); 
        
		$.ajax({
			type:"POST",
			url: url,
			data: formData,
			success: (resp) => {
				console.log(resp); 
				let obj = JSON.parse(resp);
				if(obj.result==='success'){
					Swal.fire({
						title: obj.message,
						icon: 'success',
					}).then((result)=>{
						 if(result.isConfirmed){
				
						location.href="/user/admin/list/1";
						 }
					 
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
