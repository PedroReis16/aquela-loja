class MenuToggle extends HTMLElement {
    constructor() {
        super();

        // Attach shadow DOM
        const shadow = this.attachShadow({ mode: 'open' });

        // Template for the custom element
        shadow.innerHTML = `
        <style>
          .menu-button {
            background: none;
            border: none;
            cursor: pointer;
            padding: 15px;
            position: relative;
            width: 28px;
            height: 28px;
          }

          .menu-icon {
            position: relative;
            width: 28px;
            height: 3px;
            background: var(--white);
            transition: all 0.3s ease;
          }

          .menu-icon::before,
          .menu-icon::after {
            content: '';
            position: absolute;
            width: 30px;
            height: 3px;
            background: var(--white);
            transition: all 0.3s ease;
          }

          .menu-icon::before {
            transform: translateY(-10px);
          }

          .menu-icon::after {
            transform: translateY(10px);
          }

          /* Animação para o X */
          .menu-button.active .menu-icon {
            background: transparent;
          }

          .menu-button.active .menu-icon::before {
            transform: rotate(45deg);
          }

          .menu-button.active .menu-icon::after {
            transform: rotate(-45deg);
          }
        </style>
        <button class="menu-button">
          <div class="menu-icon"></div>
        </button>
      `;

        // Add event listener for toggle behavior
        this.button = shadow.querySelector('.menu-button');
        this.button.addEventListener('click', () => {
            this.button.classList.toggle('active');
            this.dispatchEvent(new CustomEvent('toggle', { detail: { active: this.isActive } }));
        });
    }

    // Get active state
    get isActive() {
        return this.button.classList.contains('active');
    }

    // Set active state
    set isActive(value) {
        if (value) {
            this.button.classList.add('active');
        } else {
            this.button.classList.remove('active');
        }
    }
}

// Define the custom element
customElements.define('menu-toggle', MenuToggle);