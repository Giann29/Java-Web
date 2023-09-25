async function registerUser() {
    let data = {}
    let errors = {};
    data.name = document.getElementById('inputName').value;
    data.surname = document.getElementById('inputSurname').value;
    data.email = document.getElementById('inputEmail').value;
    data.phoneNumber = document.getElementById('inputPhoneNumber').value;
    data.password = document.getElementById('inputPassword').value;
    errors.nameError = document.getElementById('nameError');
    errors.surnameError = document.getElementById('surnameError');
    errors.emailError = document.getElementById('emailError');
    errors.phoneNumberError = document.getElementById('phoneNumberError');
    errors.passwordError = document.getElementById('passwordError');
    errors.repeatPasswordError = document.getElementById('repeatPasswordError');
    let repeatPassword = document.getElementById('repeatPassword').value;
    validateField(data.name, errors.nameError, "Username is required");
    validateField(data.surname, errors.surnameError, "Surname is required");
    validateField(data.email, errors.emailError, "Email is required");
    validateField(data.phoneNumber, errors.phoneNumberError, "Phone number is required");
    validatePassword(data.password, repeatPassword, errors.passwordError, errors.repeatPasswordError,);
    const request = await fetch("api/users",{
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
    });
    alert("Your account has been created successfully");
    window.location.href = "login.html";
}
function validateField(fieldValue, errorElement, errorMessage) {
  if (fieldValue.trim() === "") {
      errorElement.textContent = errorMessage;
      return false;
  } else {
      errorElement.textContent = "";
      return true;
  }
}
function validatePassword(password, repeatPassword, errorElement1, errorElement2) {
  if (password.trim() === "") {
      errorElement1.textContent = "Password is required";
      return false;
  } else if (password !== repeatPassword) {
      errorElement2.textContent = "Passwords don't match";
      return false;
  } else {
      errorElement1.textContent = "";
      errorElement2.textContent = "";
      return true;
  }
}