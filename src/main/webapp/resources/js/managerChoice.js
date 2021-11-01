let pendingB = document.getElementById("pendingReq").addEventListener('click', pending);
let resolvedB = document.getElementById("resolvedReq").addEventListener('click', resolved);
let employeesB = document.getElementById("employees").addEventListener('click', employees);

let logoutB = document.getElementById("logout").addEventListener('click', logout);

let userB = document.getElementById("userReimbs").addEventListener('submit', userReimbs);

let form = document.getElementById("updateR").addEventListener('submit', updateR);


let containerE = document.getElementById("employees-container");

async function logout(){
	let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/logout');	
	
    location.href = "http://localhost:8080/EmployeeReimbursementSystem/resources/html/Login.html";
}

async function userReimbs(e){
	e.preventDefault();
	let id = document.getElementById("userID").value;

		let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/userReimbs', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(id)
		});
		
		populateUser(req);
	
 }
 
 function populateUser(data){
			containerE.innerHTML = '';
		for(postObj of data){
		let reimbursement = document.createElement('div');
		reimbursement.innerHTML = `<h2>${postObj.amount}</h2>
						  <p>${postObj.description}</p>
						  <p>${postObj.submitted}</p>
						  <p>${postObj.resolved}</p>
						  <p>${postObj.author_id}</p>`;
		containerE.append(reimbursement);
}
}
 

async function updateR(e){
	e.preventDefault();
	
	//let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/session');
	//let res = await req.json();
	
	let id = document.getElementById("rid").value;
	let stat = document.getElementById("choiceS").value;
	
	let newR = {
		id,
		stat
	}
	
		req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/statusReim',{
		method: 'POST',
		contentType: "application/json",
		body: JSON.stringify(newR)
	});
		console.log(newR);	
		}
			



async function pending(){
			let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/allPend');
			let data = await res.json();
			populateP(data);
}

async function resolved(){
			let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/allRes');
			let data = await res.json();
			populateR(data);
}

async function employees(){
	let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/allEmploy');
	let data = await res.json();
	populateEmploy(data);
}

function populateP(data){
	containerE.innerHTML = '';
	for(postObj of data){
		let reimbursement = document.createElement('div');
		reimbursement.innerHTML = `<h4>Amount: $${postObj.amount}</h4>
						  <p>Description: ${postObj.description}</p>
						  <p>Submitted: ${postObj.submitted}</p>
						  <p>Author ID: ${postObj.author_id}</p>
						  <p>Reimbursement ID: ${postObj.id}</p>`;
		containerE.append(reimbursement);
	}	
}

function populateR(data){
			containerE.innerHTML = '';

	for(postObj of data){
		let reimbursement = document.createElement('div');
		reimbursement.innerHTML = `<h4>Amount: $${postObj.amount}</h4>
						  <p>Description: ${postObj.description}</p>
						  <p>Submitted: ${postObj.submitted}</p>
						  <p>Resolved: ${postObj.resolved}</p>
						  <p>Author ID: ${postObj.author_id}</p>`;
		containerE.append(reimbursement);
}
}

function populateEmploy(data){
			containerE.innerHTML = '';
	for(postObj of data){
		let employee = document.createElement('div');
		employee.innerHTML = `<h4>Name: ${postObj.email} ${postObj.username}</h4>
						  <p>Username: ${postObj.firstName}</p>
						  <p>ID: ${postObj.id}</p>
						  <p>Email: ${postObj.password}</p>`;
		containerE.append(employee);
}
}
