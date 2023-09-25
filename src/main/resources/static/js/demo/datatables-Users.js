// Call the dataTables jQuery plugin
$(document).ready(function() {
  logUsers();
  $('#users').DataTable();
  showUserName();
});

function showUserName() {
  document.getElementById('userName').outerHTML = localStorage.email;
}

async function logUsers() {
  const request = await fetch("api/users",{
    method: 'GET',
    headers: getHeaders()
  });
  const users = await request.json();
  let htmlList = '';
  for (let user of users) {
    let deleteButton = '<a href="#" onclick = "deleteUser(' +user.id+ ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
    let editButton = '<a href="#" class="btn btn-info btn-circle btn-sm"><i class="fas fa-info-circle"></i></a>'
    let searchButton = '<a href="#" class="btn btn-primary btn-circle btn-sm"><i class="fas fa-search"></i></a>';
    userHtml ='<tr><td>'+user.id+'</td><td>'+user.name +' '+user.surname+'</td><td>'+user.email+'</td><td>'+user.phoneNumber+'</td><td>'+
    searchButton + editButton + deleteButton + '</td></tr>';
    htmlList += userHtml;
  }
  document.querySelector('#users tbody').outerHTML = htmlList;
  console.log(users);
}

async function deleteUser(id){
  if (!confirm('Are you sure you want to delete?')){
      return;
  }
  const request = await fetch('api/users/' + id,{
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload();

}
function getHeaders(){
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  }
}