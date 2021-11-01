

let pending = document.getElementById("pendingReq").addEventListener('click', pendingCheck);

let resolved = document.getElementById("resolvedReq").addEventListener('click', resolvedCheck);

let create = document.getElementById("createR").addEventListener('submit', createReimb);

let container = document.getElementById("reimbursement-container");

let logoutB = document.getElementById("logout").addEventListener('click', logout);


	async function logout(){
	let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/logout');	
	
    location.href = "http://localhost:8080/EmployeeReimbursementSystem/resources/html/Login.html";
}
	
	async function pendingCheck(){
			let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/pending');
			let data = await res.json();
			populatePendingReq(data);
	}
	
	async function resolvedCheck(){
		let res = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/resolved');
		let data = await res.json();
		populateResolvedReq(data);
	}
	
	async function createReimb(e){
		
		e.preventDefault();
		
		
	let req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/session');
	let res = await req.json();
		
		let amount = document.getElementById("amount").value;
		let description = document.getElementById("description").value;
		let type = document.getElementById("type").value;
		let author =  res.userId;
		
		let reimbursement = {
			amount,
			description,
			type,
			author
		};
		
	req = await fetch('http://localhost:8080/EmployeeReimbursementSystem/api/create',{
		method: 'POST',
		contentType: "application/json",
		body: JSON.stringify(reimbursement)
	});
		console.log(reimbursement);	
	}
	
	function populatePendingReq(data){
		container.innerHTML = '';
		for(postObj of data){
		let reimbursement = document.createElement('div');
		reimbursement.innerHTML = `<h4>Amount: $${postObj.amount}</h4>
						  <p>Description: ${postObj.description}</p>
						  <p>Submitted: ${postObj.submitted}</p>
						  <p>Author ID: ${postObj.author_id}</p>`;
		console.log(reimbursement);				  
		container.append(reimbursement);
	}	
 }
	
	function populateResolvedReq(data){
		container.innerHTML = '';
			for(postObj of data){
		let reimbursement = document.createElement('div');
		reimbursement.innerHTML = `<h4>Amount: $${postObj.amount}</h4>
						  <p>Description: ${postObj.description}</p>
						  <p>Submitted: ${postObj.submitted}</p>
						  <p>Resolved: ${postObj.resolved}</p>
						  <p>Author ID: ${postObj.author_id}</p>`;
		console.log(reimbursement);				  
		container.append(reimbursement);
	}
	}
	