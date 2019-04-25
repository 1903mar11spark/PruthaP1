let user = {};
window.onload = function() {
    
    displayEmployeeInfo();
    
    /* document.getElementById("updateinfo").onclick=updateInfo;
    document.getElementById("submitnew").onclick=newReimbursement;*/
    document.getElementById("pending").onclick=displayPending;
    document.getElementById("approved").onclick=displayApproved;
    document.getElementById("denied").onclick=displayDenied;
    document.getElementById("viewall").onclick=displayAll;
    //document.getElementById("manageraccess").onclick=managerAccess;
    /*document.getElementById("logout").onclick=logoutUser();*/

}

function displayEmployeeInfo() {
    
    fetch("http://localhost:8084/projectone/session?type=displayEmployeeInfo").then(function(response){
   
    
    return response.json()
    }).then(function(data){
        
    employee=data;
    
    console.log(data.employeeId); 
        
    document.getElementById("firstlast").innerText = "Welcome " + data.firstName + " " + data.lastName +"!";
    document.getElementById("employeenum").innerText = "Your employee ID # is " + data.employeeId;
    document.getElementById("email").innerText = "Current email on file for your account is: " + data.email
    if (data.employeeLevel > 1) {
        document.getElementById("access").innerText = "Employee Authorization Level: " + data.employeeLevel + " -Management";
    } else {
        document.getElementById("access").innerText = "Employee Authorization Level: " + data.employeeLevel + " -Basic Employee Access";
    }});
    
}

function displayPending() {
	
	fetch("http://localhost:8084/projectone/viewpending").then(function(response){
	     
	return response.json();
	}).then(function(data){
	     
	user=data;
		
	for (let i = 0 ; i < data.length; i ++) {
		var table =  document.getElementById("rtable");
		let row = table.insertRow(0);
		let reimbId = row.insertCell(0);
		let amount =  row.insertCell(1);
		let type =  row.insertCell(2);
		let desc = row.insertCell(3);
		let status = row.insertCell(4);
		let manid =  row.insertCell(5);
	
		reimbId.setAttribute('scope', 'row');
		reimbId.innerHTML = data[i].reimbursementId;
		
		amount.setAttribute('scope', 'row');
		amount.innerHTML = data[i].reimbursementAmount;

		type.setAttribute('scope', 'row');
		type.innerHTML = data[i].reimbursementType;

		desc.setAttribute('scope', 'row');
		desc.innerHTML = data[i].reimbursementDescription;
		
		status.setAttribute('scope', 'row');
		status.innerHTML = data[i].reimbursementStatus;
		
		manid.setAttribute('scope', 'row');
		manid.innerHTML = data[i].reimbursementResolverId;

	}
	});
}

function displayApproved() {
	

	fetch("http://localhost:8084/projectone/viewapproved").then(function(response){
	     
		return response.json();
		}).then(function(data){
		     
		user=data;
		
		console.log(data);
		
		for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("rtable");
			let row = table.insertRow(0);
			let reimbId = row.insertCell(0);
			let amount =  row.insertCell(1);
			let type =  row.insertCell(2);
			let desc = row.insertCell(3);
			let status = row.insertCell(4);
			let manid =  row.insertCell(5);
		
			reimbId.setAttribute('scope', 'row');
			reimbId.innerHTML = data[i].reimbursementId;
			
			amount.setAttribute('scope', 'row');
			amount.innerHTML = data[i].reimbursementAmount;

			type.setAttribute('scope', 'row');
			type.innerHTML = data[i].reimbursementType;

			desc.setAttribute('scope', 'row');
			desc.innerHTML = data[i].reimbursementDescription;
			
			status.setAttribute('scope', 'row');
			status.innerHTML = data[i].reimbursementStatus;
			
			manid.setAttribute('scope', 'row');
			manid.innerHTML = data[i].reimbursementResolverId;


		
		}})
}

function displayDenied() {
	

	fetch("http://localhost:8084/projectone/viewdenied").then(function(response){
	     
		return response.json();
		}).then(function(data){
		     
		user=data;
		
		console.log(data);
		
		for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("rtable");
			let row = table.insertRow(0);
			let reimbId = row.insertCell(0);
			let amount =  row.insertCell(1);
			let type =  row.insertCell(2);
			let desc = row.insertCell(3);
			let status = row.insertCell(4);
			let manid =  row.insertCell(5);
		
			reimbId.setAttribute('scope', 'row');
			reimbId.innerHTML = data[i].reimbursementId;
			
			amount.setAttribute('scope', 'row');
			amount.innerHTML = data[i].reimbursementAmount;

			type.setAttribute('scope', 'row');
			type.innerHTML = data[i].reimbursementType;

			desc.setAttribute('scope', 'row');
			desc.innerHTML = data[i].reimbursementDescription;
			
			status.setAttribute('scope', 'row');
			status.innerHTML = data[i].reimbursementStatus;
			
			manid.setAttribute('scope', 'row');
			manid.innerHTML = data[i].reimbursementResolverId;


		
		}})
}

function displayAll() {
	

	fetch("http://localhost:8084/projectone/viewall").then(function(response){
	     
		return response.json();
		}).then(function(data){
		     
		user=data;
		
		console.log(data);
		
		for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("rtable");
			let row = table.insertRow(0);
			let reimbId = row.insertCell(0);
			let amount =  row.insertCell(1);
			let type =  row.insertCell(2);
			let desc = row.insertCell(3);
			let status = row.insertCell(4);
			let manid =  row.insertCell(5);
		
			reimbId.setAttribute('scope', 'row');
			reimbId.innerHTML = data[i].reimbursementId;
			
			amount.setAttribute('scope', 'row');
			amount.innerHTML = data[i].reimbursementAmount;

			type.setAttribute('scope', 'row');
			type.innerHTML = data[i].reimbursementType;

			desc.setAttribute('scope', 'row');
			desc.innerHTML = data[i].reimbursementDescription;
			
			status.setAttribute('scope', 'row');
			status.innerHTML = data[i].reimbursementStatus;
			
			manid.setAttribute('scope', 'row');
			manid.innerHTML = data[i].reimbursementResolverId;

		}})
}


