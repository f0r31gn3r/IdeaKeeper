var rootURL = "http://localhost:8080/rest/users";
var rootURLIdeas = "http://localhost:8080/rest/ideas";
var rootHTML = "http://localhost:8080";

$ = jQuery;

$(document).ready(function() {
	
	$("#userForm\\:btnSaveUser").click(function() {
		if ($("#userForm\\:userId").val() == ''){
			addUser();
		}else{
			updateUser($("#userForm\\:userId").val());
		}
	});

	$("#ideaForm\\:btnSaveIdea").click(function() {
		if ($("#ideaForm\\:ideaId").val() == '' || $("#ideaForm\\:ideaId").val() == '0'){
			addIdea($("#ideaForm\\:userId").val());
		}else{
			updateIdea($("#ideaForm\\:ideaId").val());
		}
	});

	$("#userForm\\:btnBackToMain").click(function() {
		window.location = rootHTML + "/main.html";
	});

	$("#userForm\\:btnBlockUser").click(function() {
		blockUser($("#userForm\\:userId").val());
	});

	$("#userForm\\:btnUnblockUser").click(function() {
		unblockUser($("#userForm\\:userId").val());
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
				location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation!');
			}
		});

	}

	function blockUser(id) {

		console.log('blockUser');
		console.log(id);

		$.ajax({
			type: 'PUT',
			contentType: 'application/json',
			url: rootURL + '/block/'+id,
			dataType: "json",
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				alert('User blocked successfully');
				location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation!');
			}
		});

	}

	function unblockUser(id) {

		console.log('unblockUser');
		console.log(id);

		$.ajax({
			type: 'PUT',
			contentType: 'application/json',
			url: rootURL + '/unblock/'+id,
			dataType: "json",
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				alert('User unblocked successfully');
				location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation!');
			}
		});

	}
	
	function addIdea(userId) {
		console.log('addIdea');

		$.ajax({
			type: 'POST',
			contentType: 'application/json',
			url: rootURLIdeas + "/create/" + userId,
			dataType: "json",
			data: formToJSONIdea(),
			success: function(data, textStatus, jqXHR){
				alert('Idea created successfully');
				location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation' + textStatus);
			}

		});
	}

	function updateIdea(id) {

		console.log('updateIdea');
		console.log(id);

		$.ajax({
			type: 'PUT',
			contentType: 'application/json',
			url: rootURLIdeas + '/update/'+id,
			dataType: "json",
			data: formToJSONIdea(),
			success: function(data, textStatus, jqXHR){
				alert('Idea updated successfully');
				location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation!' + textStatus + errorThrown);
			}
		});

	}

	function deleteIdea(id) {

		console.log('deleteIdea');
		console.log(id);

		$.ajax({
			type: 'DELETE',
			contentType: 'application/json',
			url: rootURLIdeas + '/delete/'+id,
			dataType: "json",
			data: formToJSONIdea(),
			success: function(data, textStatus, jqXHR){
				alert('Idea deleted successfully');
				//location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('You have no permission to perform this operation!' + textStatus + errorThrown);
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
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	function formToJSONIdea() {
		var ideaId = $("#ideaForm\\:ideaId").val();
		var currentUserId = getParameterByName('userId');
		console.log("userId: " + currentUserId);
		return JSON.stringify({
			"ideaId": ideaId == "" ? null : ideaId,
			"title": $("#ideaForm\\:title").val(),
			"description": $("#ideaForm\\:description").val(),
			"userId": currentUserId
		});
	}
});


