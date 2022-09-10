const url = "http://localhost:8080/";
// if (sessionStorage.getItem("userSession") == null){
//   window.location.replace(url + "login.html");
// }
// else{
//     let user = sessionStorage.getItem("userSession");
//     if(user.userRoledId ==1){
//         window.location.replace(url + "employee.html");
//     }
//     else{
//       window.location.replace(url + "manager.html");
//   }
// }
let reimBtn = document.getElementById("getRequests");
let submitBtn = document.getElementById("submitRequest");
let logoutBtn = document.getElementById("logoutBtn");
let reimbTable = document.getElementById("reimTable");
// add event listenrs
reimBtn.addEventListener("click", getAllRequests);
submitBtn.addEventListener("click", sendRequest);
logoutBtn.addEventListener("click", logoutFunc);

async function logoutFunc(){ // logout functionality
    
    let response = await fetch(
      url+"logout",
      {
        method : "POST", 
        credentials: "include"
      }
    );
  
    if(response.status===200){
        sessionStorage.clear();
        window.location.replace(url + "login.html");
    }else{
      console.log("Hmmm! Something went wrong!!"+response.status);
    }
}

async function sendRequest() {
   
    let typeId_value = 1;

    if (document.querySelector('#reimType').value == "Travel"){
      typeId_value = 2;
    } else if(document.querySelector('#reimType').value == "Food") {
      typeId_value = 3;
    } else if (document.querySelector('#reimType').value == "Other") {
      typeId_value = 4;
    }

    // sending reimbursement request
    console.log(document.querySelector('#reimType').value);
    let status = {
        
      amount:document.getElementById("amount").value,
      description:document.getElementById("description").value,
      typeId:typeId_value
    } 

    let response = await fetch(url + "reimbursements", {
        method:"POST",
        body:JSON.stringify(status),
        credentials:"include"
    })

    if(response.status === 201){

      
        getAllRequests(); 
        console.log("Request sent successfully!");
        
    }else {

        console.log("Hmmm! Something went wrong!!" + response.status);
    }
    
}
// view request 
async function getAllRequests(){ // we have to access only our own requests
  
    let response = await fetch(url + "reimbursements", {
      credentials:"include"

    });
  
    if(response.status === 200){

      let requests = await response.json();
      populateRequests(requests);
    
    } else{

      console.log("Hmmm! Something went wrong!!" + response.status);

    }
}

// Adding rows depend on number of records
function populateRequests(requests){
  reimbTable.innerHTML ="";

  for(let request of requests){
    let row = document.createElement("tr");
    for(let data in request){    
        let request_data = request[data];
        let td = document.createElement("td");
        td.innerText = request_data;
        row.appendChild(td); 
     
    }
    reimbTable.appendChild(row); 
  }
}
