
let form = document.getElementById("signup").addEventListener('submit', signup);

async function signup(e){
	
	e.preventDefault();
	
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
	
	try{
		let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/signup', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		location.href = '../html/Login.html';
	} catch(e){
		alert('Username or email is already registered!');
		return;
	}	
}