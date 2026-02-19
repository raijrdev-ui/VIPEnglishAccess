// Espera o HTML carregar completamente
document.addEventListener('DOMContentLoaded', () => {

    // --- LÓGICA DE CADASTRO (Primeiro Acesso) ---
    const formCadastro = document.getElementById('cadastroForm');
    if (formCadastro) {
        formCadastro.addEventListener('submit', async (e) => {
            e.preventDefault(); // Impede a página de recarregar

            // Monta o objeto exatamente como a nossa classe Usuario no Java
            const dados = {
                nomeCompleto: document.getElementById('nomeCompleto').value,
                cpf: document.getElementById('cpfCadastro').value,
                funcao: document.getElementById('funcao').value,
                telefone: document.getElementById('telefone').value,
                senha: document.getElementById('senhaCadastro').value
            };

            // Envia para o Java (Spring Boot)
            const resposta = await fetch('http://localhost:8080/api/auth/registrar', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(dados)
            });

            const resultado = await resposta.text();
            alert(resultado);
            if (resultado.includes("Sucesso")) window.location.href = 'index.html';
        });
    }

    // --- LÓGICA DE LOGIN ---
    const formLogin = document.getElementById('loginForm');
    if (formLogin) {
        formLogin.addEventListener('submit', async (e) => {
            e.preventDefault();

            const cpf = document.getElementById('cpf').value;
            const senha = document.getElementById('senha').value;

            // No Login, usamos @RequestParam, por isso passamos na URL
            const resposta = await fetch(`http://localhost:8080/api/auth/login?cpf=${cpf}&senha=${senha}`, {
                method: 'POST'
            });

            const resultado = await resposta.text();
            alert(resultado);

            if (resultado.includes("Welcome") || resultado.includes("concedido")) {
                alert("Redirecionando para o painel de gestão de alunos...");
                // No futuro, aqui iremos para a dashboard.html
            }
        });
    }
});
const API_URL = "http://localhost:8080/api/auth";

document.addEventListener('DOMContentLoaded', () => {

    // --- 1. LÓGICA DE PRIMEIRO ACESSO (CADASTRO) ---
    const formCadastro = document.getElementById('cadastroForm');
    if (formCadastro) {
        formCadastro.addEventListener('submit', async (event) => {
            event.preventDefault(); // Não deixa a página recarregar

            // Criamos um objeto JSON com os nomes EXATOS das variáveis no Java (Usuario.java)
            const novoAdmin = {
                nomeCompleto: document.getElementById('nomeCompleto').value,
                cpf: document.getElementById('cpfCadastro').value,
                funcao: document.getElementById('funcao').value,
                telefone: document.getElementById('telefone').value,
                senha: document.getElementById('senhaCadastro').value
            };

            try {
                const response = await fetch(`${API_URL}/registrar`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(novoAdmin) // Transforma o objeto em texto JSON
                });

                const mensagem = await response.text();
                alert(mensagem);

                if (response.ok && mensagem.includes("Sucesso")) {
                    window.location.href = "index.html"; // Redireciona para o Login
                }
            } catch (error) {
                console.error("Erro ao conectar com o Java:", error);
                alert("The server is offline. Check IntelliJ!");
            }
        });
    }

    // --- 2. LÓGICA DE LOGIN ---
    const formLogin = document.getElementById('loginForm');
    if (formLogin) {
        formLogin.addEventListener('submit', async (event) => {
            event.preventDefault();

            const cpf = document.getElementById('cpf').value;
            const senha = document.getElementById('senha').value;

            // Como no Controller usamos @RequestParam, os dados vão na URL (Query Params)
            try {
                const response = await fetch(`${API_URL}/login?cpf=${cpf}&senha=${senha}`, {
                    method: 'POST'
                });

                const resultado = await response.text();
                
                if (resultado.includes("concedido") || resultado.includes("Welcome")) {
                    alert("Welcome, Admin! Access granted.");
                    // window.location.href = "dashboard.html"; (Faremos essa tela depois)
                } else {
                    alert("Invalid Credentials. Try again.");
                }
            } catch (error) {
                alert("Connection failed.");
            }
        });
    }
});