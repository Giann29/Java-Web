async function logIn() {
    let data = {}
    let errors = {};
    data.email = document.getElementById('inputEmail').value;
    data.password = document.getElementById('inputPassword').value;
    errors.emailError = document.getElementById('emailError');
    errors.passwordError = document.getElementById('passwordError');
    validateField(data.email, errors.emailError, "Email is required");
    validatePassword(data.password, errors.passwordError);
    const request = await fetch("api/login",{
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
    });
    const response = await request.text();
    if (response != "Invalid") {
      localStorage.token = response;
      localStorage.email = data.email;
      window.location.href = 'users.html';
    } else {
      alert("The credentials were invalid");
    }
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
function validatePassword(password, errorElement1) {
  if (password.trim() === "") {
      errorElement1.textContent = "Password is required";
      return false;
  } else {
      errorElement1.textContent = "";
      return true;
  }
}