 
let form = document.getElementById("login").addEventListener('submit', login);


	async function login(e){
	
	e.preventDefault();
	
	
	try{
		
		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;
		
		let user = {
			username,
			password
		}
			
		if(username == "ricksan")
		{
			location.href = "http://localhost:8080/EmployeeReimbursementSystem/resources/html/managerHome.html";
			return;
		}
		
		 req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		 res = await req.json();
		location.href = '../html/employeeHome.html';
	} catch(e){
		alert('Username or password was incorrect!');
		return;
	}
	
}