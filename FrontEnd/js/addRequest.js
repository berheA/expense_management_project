let submitBtn = document.getElementById("submitRequest");

submitBtn.addEventListener("click", validateForm)

function validateForm(){
   const amount = document.getElementById("amount");
   const type = document.getElementById("reimType");
   const receipt = document.getElementById("receipt");

   if((!amount.validity.patternMismatch && !amount.validity.valueMissing) && !type.validity.valueMissing) {
    amount.classList.remove("is-invalid");
    type.classList.remove("is-invalid");
    if(fileValidation(receipt)){
        //send request
        document.getElementById("addRequest").reset();
        receipt.classList.remove("is-invalid");
    }
    else {
        receipt.classList.add("is-invalid");
    }
   } else {
   if(amount.validity.patternMismatch || amount.validity.valueMissing){
       amount.classList.add("is-invalid");
   }else {
       amount.classList.remove("is-invalid");
   }

   if(type.validity.valueMissing) {
       type.classList.add("is-invalid");
   } else {
        type.classList.remove("is-invalid");
   }
}


}


 function fileValidation(receipt) {
    const filePath = receipt.value;            
    // Allowing file type
    var allowedExtensions =
            /(\.pdf|\.jpg|\.jpeg|\.png|\.gif)$/i;
    if (!allowedExtensions.exec(filePath)) {
        return false;
    }
        return true;
                   
}