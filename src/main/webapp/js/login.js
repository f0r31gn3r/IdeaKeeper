var rootURL = "http://localhost:8080/rest/authentication";
var currentState;

$('#btnLogIn').click(function() {
	logIn();
	//return false;
});


function logIn() {
	console.log('logging in');

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + "/login",
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert(data.messageBody);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Log in error: ' + textStatus);
		}
	});
}

//function formToJSON() {
//	var userId = $('#userId').val();
//	return JSON.stringify({
//		"userId": userId == "" ? null : userId,
//		"login": $('#login').val(),
//		"password": $('#password').val(),
//		"name": $('#name').val(),
//		"surname": $('#surname').val(),
//		"email": $('#email').val(),
//		"accessLevel": $('#accessLevel').val()
//	});
//}

function formToJSON() {
	return JSON.stringify({
		"userId": "",
		"login": $('#login').val(),
		"password": $('#password').val(),
		"name": "aaa",
		"surname": "",
		"email": "",
		"accessLevel": ""
	});
}



