const userCard = document.querySelectorAll(".user-card");

//Componentes da Dialog de edição de cartão
const editCardDialog = document.getElementById('editCardDialog');
const editCardForm = document.getElementById('editCardForm');
const closeDialogBtn = document.querySelectorAll('.close-dialog-btn');

const newUserCardBtn = document.getElementById('newUserCardBtn');
const newUserCardDialog = document.getElementById('newUserCardDialog');

userCard.forEach(card => {

    //Mapeando o evento de clique em cada cartão 
    card.addEventListener('click', (event) => {
        event.preventDefault();
        const cardDetails = card.getAttribute('data-card');

        const regex = /(\w+)=([^,]*)/g;

        const result = {};
        let match;

        // Enquanto houver correspondências, adicionar ao objeto result
        while ((match = regex.exec(cardDetails)) !== null) {
            const key = match[1].trim();    // Nome da propriedade
            let value = match[2].trim();    // Valor da propriedade

            // Tentar converter para boolean, número ou manter string
            if (value === "true") value = true;
            else if (value === "false") value = false;
            else if (!isNaN(value)) value = Number(value);

            result[key] = value;
        }

        openEditCardDialog(result);
    });
});

function openEditCardDialog(result) {
    editCardDialog.querySelector('#holderName').value = result.holderName;
    editCardDialog.querySelector('#expirationDate').value = result.expirationDate;

    const deleteCardBtn = editCardDialog.querySelector('#deleteCardBtn');

    deleteCardBtn.addEventListener('click', () => {

        fetch(`/user/delete-card/${result.id}`, {
            method: 'POST'
        }).then(response => {
            window.location.reload();
        });

    });

    const saveCardBtn = editCardDialog.querySelector('#saveCardBtn');

    saveCardBtn.addEventListener('click', async (e) => {
        e.preventDefault();
        const formData = new FormData(editCardForm);

        const data = {
            holderName: formData.get('holderName'),
            expirationDate: formData.get('expirationDate')
        };

        fetch(`/user/update-card/${result.id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => {
            window.location.reload();
        });
    });

    editCardDialog.showModal();
}

newUserCardBtn.addEventListener('click', (event) => {
    event.preventDefault();
    newCardDialog.showModal();
});

closeDialogBtn.forEach(btn => {
    btn.addEventListener('click', (event) => {
        event.preventDefault();
        editCardDialog.close();
        // Adicione qualquer outra lógica necessária para fechar a dialog
    });
});

window.addEventListener('click', function (event) {
    if (event.target === editCardDialog) {
        closeDialog();
    }
    if(event.target == newCardDialog){
        newCardDialog.close();
    }
});

function closeDialog() {
    // editCardDialog.querySelectorAll('input').forEach(input => { input.value = ""; });
    editCardDialog.close();
}