
    // Get the modal and close button elements
    const modal = document.getElementById("myModal");
    const closeBtn = document.getElementsByClassName("close")[0];

    // Add event listener to all buttons with class 'more-info-btn'
    document.querySelectorAll('.more-info-btn').forEach(button => {
    button.addEventListener('click', function () {
        // Get data attributes
        const name = this.getAttribute('data-name');
        const email = this.getAttribute('data-email');
        const phone = this.getAttribute('data-phone');
        const department = this.getAttribute('data-department');
        const position = this.getAttribute('data-position');
        const dateNaissance = this.getAttribute('data-date-naissance');
        const numeroSecuriteSociale = this.getAttribute('data-numero-securite-sociale');
        const dateEmbauche = this.getAttribute('data-date-embauche');
        const adresse = this.getAttribute('data-adresse');

        console.log("adresse" , adresse)

        // Populate modal with employee information
        document.getElementById("modal-name").textContent = name;
        document.getElementById("modal-email").textContent = email;
        document.getElementById("modal-phone-display").textContent = phone;
        document.getElementById("modal-department").textContent = department;
        document.getElementById("modal-position").textContent = position;
        document.getElementById("modal-date-naissance").textContent = dateNaissance;
        document.getElementById("modal-numero-securite-sociale").textContent = numeroSecuriteSociale;
        document.getElementById("modal-date-embauche").textContent = dateEmbauche;
        document.getElementById("modal-adresse").textContent = adresse;

        // Display the modal
        modal.style.display = "block";
    });
});

    // Close the modal when the close button is clicked
    closeBtn.onclick = function() {
        modal.style.display = "none";
    }

    // Close the modal when clicking anywhere outside of the modal content
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }



// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};






