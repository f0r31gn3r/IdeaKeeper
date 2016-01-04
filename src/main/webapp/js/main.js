//function findCurrentUserIdeas(id){
//	console.log('findCurrentUserIdeas');
//	$.ajax({
//		type: 'GET',
//		url: rootURL + "/get_ideas/" + id,
//		dataType: "json",
//		success: function(data){
//			currentUserIdeas = data;
//			renderUserIdeasList(currentUserIdeas);
//		}
//	});
//}

//function renderUserIdeasList(data) {
//	var list = data == null ? [] : (data instanceof Array ? data : [data]);
//
//	$('#ideasList li').remove();
//	$.each(list, function(index, ideaDTO) {
//		$('#description').val(ideaDTO.ideaId + ' '+ideaDTO.title);
//	});
//}

var rootURL = "http://localhost:8080/rest/users";
var currentUser;

findAll();

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
	if ($('#userId').val() == '')
		addUser();
	else
		updateUser();
	return false;
});

$('#btnDelete').click(function() {
	deleteUser();
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

function findByName(searchKey) {

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
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addUser error: ' + textStatus);
		}
	});
}

function updateUser() {

	console.log('updateUser');

	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/update/',
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('User updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateUser error: ' + textStatus);
		}
	});

}

function deleteUser() {
	console.log('deleteUser');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/delete/' + $('#userId').val(),
		success: function(data, textStatus, jqXHR){
			alert('User deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteUser error');
		}
	});
}

function renderList(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#userList li').remove();
	$.each(list, function(index, userDTO) {
		$('#userList').append('<li><a href="#" data-identity="' + userDTO.userId + '">'+userDTO.login+'</a></li>');
	});
}

function renderDetails(userDTO) {
	$('#userId').val(userDTO.userId);
	$('#login').val(userDTO.login);
	$('#password').val(userDTO.password);
	$('#name').val(userDTO.name);
	$('#surname').val(userDTO.surname);
	$('#email').val(userDTO.email);
	$('#accessLevel').val(userDTO.accessLevel);
	//$('#pic').attr('src', 'pics/' + userDTO.picture);
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
