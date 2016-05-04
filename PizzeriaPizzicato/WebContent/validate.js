function validate(form){
  
  var error = "";
  
  error += validateEmpty(form.elements);
  error += validateUsername(form.username);
  error += validatePassword(form.password);
  error += validateEmail(form.email);
  error += validatePhone(form.phone);
  
  if(error){
      document.querySelector('.error').innerText = "Invalid inputs:\n" + error;
      return false;
  }
};


function validateEmpty(elements) {
    var error = "";
    for(element of Array.from(elements)){
        if(element.type != "submit" && element.value.length < 3){
            error="Each field must have atleast 3 chars.\n";
            element.style.border = "2px solid crimson";
        }
    }
    return error;   
}
  
  
  function validateUsername(fld) {
    var error = "";
    var illegalChars = /\W/; // allow letters, numbers, and underscores
 
    if (fld.value == "") {
        error = "You didn't enter a username.\n";
    } else if ((fld.value.length < 5) || (fld.value.length > 15)) {
        error = "The username must be between 5 and 15 chars.\n";
    } else if (illegalChars.test(fld.value)) {
        error = "The username contains illegal characters.\n";
    }
    if(error) fld.style.border = "2px solid crimson";
    return error;
}
  
  function validatePassword(fld) {
    var error = "";
    var illegalChars = /[\W_]/; // allow only letters and numbers 
 
    if (fld.value == "") {
        error = "You didn't enter a password.\n";
    } else if ((fld.value.length < 7) || (fld.value.length > 15)) {
        error = "The password must be between 7 and 15 chars. \n";
    } else if (illegalChars.test(fld.value)) {
        error = "The password contains illegal characters.\n";
    } else if (!((fld.value.search(/(a-z)+/)) && (fld.value.search(/(0-9)+/)))) {
        error = "The password must contain at least one numeral.\n";
    }
    if(error) fld.style.border = "2px solid crimson";
   return error;
}  

function trim(s)
{
  return s.replace(/^\s+|\s+$/, '');
} 

function validateEmail(fld) {
    var error="";
    var tfld = trim(fld.value);                        // value of field with whitespace trimmed off
    var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
    var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
    
    if (fld.value == "") {
        error = "You didn't enter an email address.\n";
    } else if (!emailFilter.test(tfld)) {              //test email for illegal characters
        error = "Please enter a valid email address.\n";
    } else if (fld.value.match(illegalChars)) {
        error = "The email address contains illegal characters.\n";
    }
    if(error) fld.style.border = "2px solid crimson";
    return error;
}
  
  function validatePhone(fld) {
    var error = "";
    var stripped = fld.value.replace(/[\(\)\.\-\ ]/g, '');     

   if (fld.value == "") {
        error = "You didn't enter a phone number.\n";
    } else if (isNaN((stripped))) {
        error = "The phone number contains illegal characters.\n";
    } else if (!(stripped.length == 10)) {
        error = "The phone number is the wrong length. Make sure you included an area code.\n";
    } 
    if(error) fld.style.border = "2px solid crimson";
    return error;
}