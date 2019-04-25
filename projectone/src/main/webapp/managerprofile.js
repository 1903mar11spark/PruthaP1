let user = {};
window.onload = function(){

    displayManagerInfo();

    //document.getElementById("tabletitle").innerText="Current Pending Requests";
    document.getElementById("allpending").onclick=displayPendingMan;
}

function displayManagerInfo() {
    
    fetch("http://localhost:8084/projectone/session?type=displayEmployeeInfo").then(function(response){
   
    
    return response.json();
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

function displayPendingMan(){
	
	fetch("http://localhost:8084/projectone/managerviewpending").then(function(response){
		
		return response.json();
		}).then(function(data){
			
		employee=data;
		
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
		}
	})
}
