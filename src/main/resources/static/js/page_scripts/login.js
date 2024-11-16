const emailInput = document.getElementById("emailInput");
const passwordInput = document.getElementById("passwordInput");
const loginBtn = document.getElementById("loginBtn");

const togglePasswordBtn = document.getElementById("togglePasswordBtn");

document.addEventListener("DOMContentLoaded", function () {
    //Verificando se as credenciais estão preenchidas
    function checkInputs() {
        const isEmailFilled = emailInput.value.trim() !== "";
        const isPasswordFilled = passwordInput.value.trim() !== "";

        loginBtn.disabled = !(isEmailFilled && isPasswordFilled);
    }

    emailInput.addEventListener("input", checkInputs);
    passwordInput.addEventListener("input", checkInputs);
    checkInputs();
});

togglePasswordBtn.addEventListener("click", function () {
    const type = passwordInput.getAttribute("type") === "password" ? "text" : "password";
    passwordInput.setAttribute("type", type);

    togglePasswordBtn.classList.toggle("visible");
});
