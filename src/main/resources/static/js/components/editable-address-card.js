class EditableAddressCard extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: 'open' });

        // Definindo os atributos do endereço
        const attributes = ['address-identification', 'street', 'number-string', 'details', 'reference'];

        // Criando o elemento base do card
        const card = document.createElement('div');
        card.setAttribute('class', 'address-card');

        // Criando a DIV que irá conter o conteúdo do card
        const cardContent = document.createElement('div');
        cardContent.setAttribute('class', 'card-content');

        // Criando e adicionando os elementos de conteúdo
        attributes.forEach(attr => {
            const element = document.createElement(attr === 'address-identification' ? 'h3' : 'p');
            element.setAttribute('id', attr);
            cardContent.appendChild(element);
        });

        // Definindo o a div de ações do card e o botão de edição
        const cardActions = document.createElement('div');
        cardActions.setAttribute('class', 'card-actions');
        const editAddressButton = document.createElement('button');
        editAddressButton.setAttribute('type', 'button');
        editAddressButton.setAttribute('class', 'edit-address-btn');
        editAddressButton.textContent = 'Editar';

        // Juntando as partes do conteúdo na DIV de conteúdo
        cardActions.appendChild(editAddressButton);

        // Juntando tudo ao componente principal
        card.appendChild(cardContent);
        card.appendChild(cardActions);

        // Definindo o estilo do card
        const style = document.createElement('style');
        style.textContent = `
            .address-card {
                border: 1px solid #ccc;
                padding: 16px;
                margin: 8px 0;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .card-content {
                margin-bottom: 16px;
            }
            .card-actions {
                text-align: right;
            }
            .edit-address-btn {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                cursor: pointer;
            }`;

        shadow.appendChild(style);
        shadow.appendChild(card);
    }

    static get observedAttributes() {
        return ['address-identification', 'street', 'number-string', 'details', 'reference'];
    }

    attributeChangedCallback(name, _, newValue) {
        this.shadowRoot.getElementById(name).textContent = newValue;
    }
}

customElements.define('editable-address-card', EditableAddressCard);
