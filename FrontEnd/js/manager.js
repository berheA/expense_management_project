

// const url = "http://localhost:8080/"

// if (sessionStorage.getItem("userSession") == null){
//     window.location.replace(url + "login.html");
// }else{
//     let user = sessionStorage.getItem("userSession");
//     if(user.userRoledId ==1){
//         window.location.replace(url + "employee.html");
//     }
//     else{
//       window.location.replace(url + "manager.html");
//   }
// }

// let statusUpdate = document.querySelector('#reimpType');
// let submitBtn = document.getElementById("updateStatusBtn");
// let reimTable = document.getElementById("reimTabl");
// let reimBtn = document.getElementById("getRequests");
// let loginBtn = document.getElementById("loginBtn");
// let filterUpdate = document.getElementById("#searchReimStatus");
// let getListBtn = document.getElementById("getList");


// submitBtn.addEventListener("click", setStatus);
// reimBtn.addEventListener("click", getAllRequests);
// logoutBtn.addEventListener("click", logoutFunc);
// getListBtn.addEventListener("click", getAllRequestsById);

// async function logoutFunc(){
    
  
//     let response = await fetch(
//       url+"logout",
//       {
//         method : "POST",   
//         credentials: "include"
//       }
//     );
  
//     if(response.status===200){
//         sessionStorage.clear();
//         window.location.replace(url + "login.html");
//     }else{
//       console.log("Logout unsuccessful. Returned status code of:"+response.status);
//     }
// }

// async function setStatus() {
   
//     // visual feedback of status update choice
//     output = statusUpdate.value;
//     document.querySelector('.output').textContent = output;

//     let statusId_value = 1;

//     if (document.querySelector('#reimStatus').value == "denied"){
//       statusId_value = 2;
//     } else if(document.querySelector('#reimStatus').value == "approved") {
//       statusId_value = 3;
//     } 

//     console.log(document.querySelector('#reimStatus').value);

//     // setting status update choice as value for put request
//     let status = {
//         reimId: parseInt(document.getElementById("reimId").value),

//         statusId: statusId_value
//     } 


//     let response = await fetch(url + "reimbursements", {
//         method:"PUT",
//         body:JSON.stringify(status),
//         credentials:"include"
//     })

//     if(response.status === 200){
//         getAllRequests(); 
//         console.log("Request status updated successfully!");
//     }else {
//         console.log("Request update failed! Try it again.");
//     }
// }

// // All reimbursement Requests' list
// async function getAllRequests(){
    

//     let response = await fetch(url + "reimbursements", {
//       credentials:"include"

//     });
  
//     if(response.status === 200){

//       let requests = await response.json();
//       populateRequests(requests);
    
//     } else{

//       console.log("No reimbursement list found!")

//     }
// }

// // view reimbursement list by id
// async function getAllRequestsById(){
//   let statusId_value = 1;
  
//   if (document.querySelector('#searchReimStatus').value == "denied"){
//       statusId_value = 2;
//   } else if(document.querySelector('#searchReimStatus').value == "approved") {
//       statusId_value = 3;
//   } 

//   let response = await fetch(url + "reimbursements/" + statusId_value , {
//     credentials:"include"
//   });

//   if(response.status === 200){

//     let requests = await response.json();
//     populateRequests(requests);
  
//   } else{

//     console.log("No reimbursement list found!");

//   }
// }

// // Adding rows depend on number of records
// function populateRequests(requests){
//   reimbTable.innerHTML ="";

//   for(let request of requests){
//     let row = document.createElement("tr");
//     for(let data in request){    
//         let request_data = request[data];
//         let td = document.createElement("td");
//         td.innerText = request_data;
//         row.appendChild(td); 
     
//     }
//     reimbTable.appendChild(row); 
//   }
// }


let filterBtn = document.getElementById("filterBtn");

filterBtn.addEventListener("click", validateFilter);

function validateFilter(){
  const empID = document.getElementById("empID");

  if(empID.validity.patternMismatch || empID.validity.valueMissing){
    empID.classList.add("is-invalid");
  }
  else {
    empID.classList.remove("is-invalid");
    //submit the request
    document.getElementById("filterForm").reset();
  }
}