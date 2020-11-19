let userId = document.getElementById('userId');
let userName = document.getElementById('userName');
let userPw = document.getElementById('userPw');
let sendBtn = document.getElementById('send');

sendBtn.addEventListener('click', () => {
    let obj = {
        userId : userId.value,
        userName : userName.value,
        userPw : userPw.value
    }

    $.ajax({
        url: '/api/signUp',
        type: 'POST',
        data: obj,
    })
    .then((data) => {
        console.log('success');
        alert("Success");
        location.href = '/';
    })
    .catch((error) => {
        alert("Fail");
    	console.log(error);
    })



})
