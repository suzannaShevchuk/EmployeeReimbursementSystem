
getUser();

let container = document.getElementById("user-container");

let update = document.getElementById("updateAccount").addEventListener('submit', updateUser);
let logoutB = document.getElementById("logout").addEventListener('click', logout);


async function getUser(){
			let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/user');
			let data = await res.json();
			showUser(data);
}

async function logout(){
	let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/logout');	
	
    location.href = "http://localhost:8080/EmployeeReimbursementSystem/resources/html/Login.html";
}


function showUser(data){
		
		let user = document.createElement('div');
		user.innerHTML = `<h4>Username: ${data.username}</h4>
						  <p>Password: ${data.password}</p>
						  <p>Name: ${data.firstName} ${data.lastName}</p>
						  <p>Email: ${data.email}</p>`;
		console.log(user);				  
		container.append(user);
		
 }

 async function updateUser(){	
   container.innerHTML = '';

	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	let first = document.getElementById("first").value;
	let last = document.getElementById("last").value;
	let email = document.getElementById("email").value;
	
	let user = {
		username,
		password,
		first,
		last,
		email
	}
	
		let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/update', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		getUser();
		location.href = '../html/employeeHome.html';
		
	
}