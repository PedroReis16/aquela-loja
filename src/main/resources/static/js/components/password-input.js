class PasswordInput extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: 'open' });

        const base = document.createElement('div');
        base.setAttribute('class', 'passoword-input');

        const icon = document.createElement('i');
        icon.setAttribute('id', 'lockIcon');

        const input = document.createElement('input');
        input.setAttribute('type', 'password'); // Alterado para 'password'


        const visibleButton = document.createElement('button');
        visibleButton.setAttribute('class', 'toggle-password');
        visibleButton.setAttribute('type', 'button'); // Adicionado para evitar submissão de formulário

        const visibleIcon = document.createElement('i');
        visibleIcon.setAttribute('id', 'visibleIcon');

        visibleButton.appendChild(visibleIcon);

        base.appendChild(icon);
        base.appendChild(input);
        base.appendChild(visibleButton);

        shadow.appendChild(base);

        const style = document.createElement('style');

        // Propriedades para o placeholder
        const placeholderValue = this.getAttribute('placeholder') ?? "";
        input.setAttribute('placeholder', placeholderValue);

        // Propriedades para o valor
        const value = this.getAttribute('value') ?? "";
        input.value = value;

        // Propriedades para o ícone
        const iconSize = this.getAttribute('icon-size') ?? '24px';

        style.textContent = `
            .passoword-input {
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
            .passoword-input input {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: none;
                outline: none;
            }
            .passoword-input > i {
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .passoword-input > i::before {
                content: "\e897";
                font-family: var(--material-icons);
                color: var(--gray);
                font-size: 24px;
            }
            .toggle-password {
                background-color: transparent;
                border: none;
                outline: none;  
                cursor: pointer;
            }
            #visibleIcon::before {
                content: '\e8f5';
                font-family: var(--material-icons);
                color: var(--gray);
                font-size: 24px;
            }
        `;

        shadow.appendChild(style);

        // Adicionando evento para alternar visibilidade da senha
        visibleButton.addEventListener('click', () => {
            const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
            input.setAttribute('type', type);
            visibleButton.classList.toggle('visible');
        });
    }
}

customElements.define('password-input', PasswordInput);