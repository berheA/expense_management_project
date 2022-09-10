
let loginBtn = document.getElementById("loginBtn");

const url = "http://localhost:7000/";
//const url = "http://18.208.181.129:7000/";
/*
if (sessionStorage.getItem("userSession") == null){
  logoutBtn.innerHTML = "";
}
*/
loginBtn.addEventListener("click", loginInputValidate);

async function loginFunc(username, password){
  
    let user = {
        username: username,
        password: password
    };

    console.log(user);

//     let response = await fetch(
//         url+"login",
//         {
//           method : "POST",
//           body : JSON.stringify(user),
//           credentials: "include" //.......TODO
//         }
//       );
    
//       if(response.status===200){
//         let user_info = response.json();
//         sessionStorage.setItem("userSession", user_info);

          //}
//         if (user_info.userRoleId == 1) {
//             window.location.replace(url + "employee.html");
//         } else {
//             window.location.replace(url + "manager.html");
//         }
//       }else{
//         console.log("Incorrect info "+response.status);
//       }
document.getElementById("loginForm").reset();
}


function loginInputValidate(){  
  const username = document.getElementById("username");
  const password = document.getElementById("password");

  if(username.checkValidity() && password.checkValidity()) {
    loginFunc(username.value, password.value);
    username.classList.remove("is-invalid");
    password.classList.remove("is-invalid");
  } 
  else {

  if(!username.checkValidity()) {
    username.classList.add("is-invalid");
  }
  else{
    username.classList.remove("is-invalid");
  }

  if(!password.checkValidity()){
    password.classList.add("is-invalid");
  }else {
    password.classList.remove("is-invalid");
  }
}
}  