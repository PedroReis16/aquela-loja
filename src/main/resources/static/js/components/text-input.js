class TextInput extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: 'open' });

        const base = document.createElement('div');
        base.setAttribute('class', 'input-label');

        const icon = document.createElement('i');
        icon.setAttribute('id', 'icon');

        const input = document.createElement('input');
        input.setAttribute('type', 'text');
        input.setAttribute('id', 'input');

        base.appendChild(icon);
        base.appendChild(input);

        shadow.appendChild(base);

        const style = document.createElement('style');

        //Propriedades para o placeholder
        const placeholderValue = this.getAttribute('placeholder') ?? "";
        input.setAttribute('placeholder', placeholderValue);

        //Propriedades para o valor
        const value = this.getAttribute('value') ?? "";
        input.value = value;

        //Propriedades para o ícone
        const iconValue = this.getAttribute('icon');
        const iconSize = this.getAttribute('icon-size') ?? '24px';

        style.textContent = `
            .input-label {
                position: relative;
                margin: 0px 0;
                font-family: Arial, sans-serif;
                display: grid;
                grid-template-columns: 32px 1fr 48px;
                align-content: center;
                background-color: var(--white, #ffffff);
                border: 1px solid var(--gray, #d9d9d9);
                border-radius: 4px;
                outline: none;
                box-sizing: border-box;
                height: 56px;
            }
            .input-label input {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: none;
                outline: none;
            }
            .input-label > i {
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .input-label > i::before {
                content: "${iconValue}";
                font-family: var(--material-icons);
                color: var(--gray);
                font-size: ${iconSize};
            }
        `;

        shadow.appendChild(style);
    }
}

customElements.define('text-input', TextInput);