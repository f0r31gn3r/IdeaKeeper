var rootURL = "http://localhost:8080/rest/authentication";

$('#btnLogIn').click(function() {
	logIn();
	return false;
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
		error: function(textStatus){
			alert('Global error occured: ' + textStatus);
		}
	});
}

function formToJSON() {
	return JSON.stringify({
		"userId": "",
		"login": $('#login').val(),
		"password": $('#password').val(),
		"name": "",
		"surname": "",
		"email": "",
		"accessLevel": ""
	});
}



