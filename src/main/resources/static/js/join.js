let username = document.getElementById('username');
let password = document.getElementById('password');
let email = document.getElementById('email');
let sendBtn = document.getElementById('sendBtn');

sendBtn.addEventListener('click', () => {
	
    let obj = {
        username : username.value,
        password : password.value,
        email : email.value
    }
    
	ajaxConnect('/api/join', 'POST', obj)
    .then((data) => {
    	if(data && data == 200){
	        console.log('success');
	        alert("Success");
	        location.href = '/login';
    	}else if(data == 409){
    		alert("Already existed Username");
    		userId.select();
    	}
    })
    .catch((error) => {
        alert("Fail");
    	console.log(error);
    })



})
