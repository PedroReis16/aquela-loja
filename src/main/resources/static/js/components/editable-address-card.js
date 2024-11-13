class EditableAddressCard extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });
        this.shadowRoot.innerHTML = `
            <style>
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
                }
            </style>
            <div class="address-card">
                <div class="card-content">
                    <h3 id="address-identification"></h3>
                    <p id="street"></p>
                    <p id="number-string"></p>
                    <p id="details"></p>
                    <p id="reference"></p>
                </div>
                <div class="card-actions">
                    <button type="button" class="edit-address-btn">Editar</button>
                </div>
            </div>
        `;
    }

    static get observedAttributes() {
        return ['address-identification', 'street', 'number-string', 'details', 'reference'];
    }

    attributeChangedCallback(name, _, newValue) {
        this.shadowRoot.getElementById(name.replace(/-/g, '')).textContent = newValue;
    }
}

customElements.define('editable-address-card', EditableAddressCard);
