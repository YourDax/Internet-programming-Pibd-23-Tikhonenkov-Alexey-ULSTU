function validation() {
    const forms = document.querySelectorAll("form.needs-validation");

    for (let i = 0; i < forms.length; i += 1) {
        const form = forms[i];
        form.addEventListener("submit", (event) => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add("was-validated");
        });
    }
}

export default validation;