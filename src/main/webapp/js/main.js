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
		findByName(searchKey);
}

function newUser() {

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

}

function updateUser() {

}

function deleteUser() {

}

function renderList(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#userList li').remove();
	$.each(list, function(index, userDTO) {
		$('#userList').append('<li><a href="#" data-identity="' + userDTO.userId + '">'+userDTO.name+'</a></li>');
	});
}

function renderDetails(userDTO) {
	$('#userId').val(userDTO.userId);
	$('#name').val(userDTO.name);
	$('#surname').val(userDTO.surname);
	$('#email').val(userDTO.email);
	$('#pic').attr('src', 'pics/' + userDTO.picture);
}

function formToJSON() {
	var userId = $('#userId').val();
	return JSON.stringify({
		"id": userId == "" ? null : userId,
		"name": $('#name').val(), 
		"surname": $('#surname').val(),
		"email": $('#email').val(),
		"picture": currentUser.picture,
		"description": $('#description').val()
		});
}
