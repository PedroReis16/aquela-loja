class CustomCard extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: 'open' });

        const template = document.createElement('template');
        template.innerHTML = `
            <style>
                .card {
                    background-color: var(--white);
                    padding: 30px;
                    border-radius: 10px;
                    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                    text-align: center;
                }
            </style>
            <div class="card">
                <slot></slot>
            </div>
        `;

        shadow.appendChild(template.content.cloneNode(true));
    }
}

customElements.define('custom-card', CustomCard);