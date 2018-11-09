function getUtilisateurs() {
  document.getElementById("data-list").innerHTML = "";
  $.ajax({
    url: 'http://localhost:8080/HelloWorld/users',
    dataType: 'application/json',
    complete: function(data){
        let dataResponse = data["responseText"];
        document.getElementById("data-list").innerHTML = dataResponse;
        console.log(data);
    }
  })
}

function postUtilisateur() {
  document.getElementById("data-list").innerHTML = "";
  let firstname = document.querySelector("#firstname").value;
  let lastname = document.querySelector("#lastname").value;
  $.ajax({
    url: 'http://localhost:8080/HelloWorld/user',
    dataType: 'application/json',
    type: 'POST',
    contentType: 'application/json',
    data: JSON.stringify( { "Firstname": firstname, "Lastname": lastname } ),
    processData: false,
    complete: function(data){
       let dataResponse = data["responseText"];
       document.getElementById("data-list").innerHTML = dataResponse;
       console.log(data);
    }
  })
}

function deleteUtilisateur() {
  document.getElementById("data-list").innerHTML = "";
  let firstname = document.querySelector("#firstname").value;
  let lastname = document.querySelector("#lastname").value;
  $.ajax({
    url: 'http://localhost:8080/HelloWorld/user',
    dataType: 'application/json',
    type: 'DELETE',
    contentType: 'application/json',
    data: JSON.stringify( { "Firstname": firstname, "Lastname": lastname } ),
    processData: false,
    complete: function(data){
       let dataResponse = data["responseText"];
       document.getElementById("data-list").innerHTML = dataResponse;
       console.log(data);
    }
  })
}

function modifyUtilisateur() {
  document.getElementById("data-list").innerHTML = "";
  let firstname = document.querySelector("#firstname").value;
  let lastname = document.querySelector("#lastname").value;
  let newfirstname = document.querySelector("#newfirstname").value;
  let newlastname = document.querySelector("#newlastname").value;
  $.ajax({
    url: 'http://localhost:8080/HelloWorld/user',
    dataType: 'application/json',
    type: 'PUT',
    contentType: 'application/json',
    data: JSON.stringify( { "Firstname": firstname, "Lastname": lastname, "newFirstname": newfirstname, "newLastname": newlastname } ),
    processData: false,
    complete: function(data){
       let dataResponse = data["responseText"];
       document.getElementById("data-list").innerHTML = dataResponse;
       console.log(data);
    }
  })
}