var rootURL = "http://localhost:8080/rest/users";
var currentUser;

findAll();
newUser();

$('#btnDelete').hide();

$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
	}
});

$('#btnAdd').click(function() {
	newUser();
	return false;
});

$('#btnSave').click(function() {
	if ($('#userId').val() == ''){
		addUser();
	}else{
		updateUser($('#userId').val());
	}
	return false;
});

$('#btnDelete').click(function() {
	deleteUser();
	return false;
});

$('#btnEdit').click(function() {
	window.location = 'http://localhost:8080/editUserProfile.faces?userId=' + $('#userId').val();
	return false;

});

$('#userList a').live('click', function() {
	findById($(this).data('identity'));
});

$("img").error(function(){
	$(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '')
		findAll();
	else
		findById(searchKey);
}

function newUser() {
	$('#btnDelete').hide();
	$('#ideasList li').remove();
	currentUser = {};
	renderDetails(currentUser); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json",
		success: renderList
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/get/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentUser = data;
			renderDetails(currentUser);
		}
	});
}

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

function deleteUser() {
	console.log('deleteUser');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/delete/' + $('#userId').val(),
		success: function(data){
			alert('User deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('You have no permission to perform this operation!');
		}
	});
}

function sendEmail() {
	console.log('sending Email');

	$.ajax({
		url: 'http://localhost:8080/amq/call_queue1',
	});
}

function renderList(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#userList li').remove();
	$.each(list, function(index, userDTO) {
		$('#userList').append('<li><a href="#" data-identity="' + userDTO.userId + '">'+userDTO.login+'</a></li>');
	});
}

function findCurrentUserIdeas(id){
	console.log('findCurrentUserIdeas');
	$.ajax({
		type: 'GET',
		url: rootURL + "/get_ideas/" + id,
		dataType: "json",
		success: function(data){
			currentUserIdeas = data;
			renderUserIdeasList(currentUserIdeas);
		}
	});
}

function renderUserIdeasList(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#ideasList li').remove();
	$.each(list, function(index, ideaDTO) {
		$('#ideasList').append('<li><a href="#" data-identity="' + ideaDTO.ideaId + '">'+ideaDTO.title + ': ' + ideaDTO.description+'</a></li>')
	});
}

function renderDetails(userDTO) {
	findCurrentUserIdeas(userDTO.userId)

	$('#userId').val(userDTO.userId);
	$('#login').val(userDTO.login);
	$('#password').val(userDTO.password);
	$('#name').val(userDTO.name);
	$('#surname').val(userDTO.surname);
	$('#email').val(userDTO.email);
	$('#accessLevel').val(userDTO.accessLevel);
	$('#pic').attr('src', 'pics/' + userDTO.userId + ".jpg");
}

function formToJSON() {
	var userId = $('#userId').val();
	return JSON.stringify({
		"userId": userId == "" ? null : userId,
		"login": $('#login').val(),
		"password": $('#password').val(),
		"name": $('#name').val(),
		"surname": $('#surname').val(),
		"email": $('#email').val(),
		"accessLevel": $('#accessLevel').val()
	});
}
