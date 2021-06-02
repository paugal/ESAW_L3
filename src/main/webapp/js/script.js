const form = document.querySelector("form");
const email = document.getElementById("mail");
const pwd1 = document.getElementById("pwd1");
const pwd2 = document.getElementById("pwd2");
const fullname = document.getElementById("fullName");

var checkPasswordValidity = function() {
	 if (pwd2.value !== pwd1.value ) {
		pwd2.setCustomValidity("Passwords must match!");
	} else {
		pwd2.setCustomValidity("");
	}
}


email.addEventListener("input", function (event) {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("I am expecting an e-mail address!");
  } else {
    email.setCustomValidity("");
  }
});

pwd2.addEventListener("input", checkPasswordValidity, false);

form.addEventListener("submit", function (event) {
	checkPasswordValidity();
	if (!this.checkValidity()) {
		this.reportValidity();
		event.preventDefault();
	} 
}, false);

if( document.getElementById('sqlError').innerHTML == "" ){
	document.getElementById('sqlError').style.display = "none";
}