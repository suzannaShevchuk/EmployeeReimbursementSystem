
getUser();

let container = document.getElementById("user-container");

let update = document.getElementById("updateAccount").addEventListener('submit', updateUser);

async function getUser(){
			let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/user');
			let data = await res.json();
			showUser(data);
}



function showUser(data){
		
		let user = document.createElement('div');
		user.innerHTML = `<h2>${data.username}</h2>
						  <p>${data.password}</p>
						  <p>${data.firstName} ${data.lastName}</p>
						  <p>${data.email}</p>`;
		console.log(user);				  
		container.append(user);
		
 }

 async function updateUser(){	
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
		//location.href = '../html/employeeHome.html';
	
}