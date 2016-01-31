var rootURL = "http://localhost:8080/rest/users";

$ = jQuery;

$(document).ready(function() {

	$("#userForm\\:btnSave").click(function() {
		if ($("#userForm\\:userId").val() == ''){
			addUser();
		}else{
			updateUser($("#userForm\\:userId").val());
		}
	});

	function addUser() {
		console.log('addUser');

		$.ajax({
			type: 'POST',
			contentType: 'application/json',
			url: rootURL + "/create",
			dataType: "json",
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				alert('User created successfully');
				$('#btnDelete').show();
				$('#userId').val(data.id);
				sendEmail();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation' + textStatus);
			}

		});
	}

	function updateUser(id) {

		console.log('updateUser');
		console.log(id);

		$.ajax({
			type: 'PUT',
			contentType: 'application/json',
			url: rootURL + '/update/'+id,
			dataType: "json",
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				alert('User updated successfully');
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation!');
			}
		});

	}

	function formToJSON() {
		var userId = $("#userForm\\:userId").val();
		return JSON.stringify({
			"userId": userId == "" ? null : userId,
			"login": $("#userForm\\:login").val(),
			"password": $("#userForm\\:password").val(),
			"name": $("#userForm\\:name").val(),
			"surname": $("#userForm\\:surname").val(),
			"email": $("#userForm\\:email").val(),
			"accessLevel": $("#userForm\\:accessLevel").val()
		});
	}
});


