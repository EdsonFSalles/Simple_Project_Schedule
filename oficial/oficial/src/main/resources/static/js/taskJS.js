const CORES = {
    barra: {
        fundo:       "#ffffff",
        semPrazo:    "#e0e0e0",
        naoIniciado: "#bfbfbf",
        andamento:   "#666666",
        finalizado:  "#000000",
        atrasado:    "#1a1a1a"
    },
    texto: {
        principal:   "#000000",
        secundario:  "#1a1a1a",
        suave:       "#4d4d4d",
        claro:       "#808080"
    }
};

document.addEventListener("DOMContentLoaded", () => {

    document.querySelectorAll(".task-card").forEach(card => {
        const startStr = card.dataset.start;
        const endStr   = card.dataset.end;
        const state    = card.dataset.state;

        const bar   = card.querySelector(".progress-bar");
        const label = card.querySelector(".progress-label");

        if (!bar || !label) return;

        if (!startStr || !endStr) {
            bar.style.width = "0%";
            bar.style.backgroundColor = CORES.barra.semPrazo;
            label.textContent = "SEM PRAZO";
            label.style.color = CORES.texto.suave;
            return;
        }

        const start = new Date(startStr + "T00:00:00");
        const end   = new Date(endStr   + "T23:59:59");
        const hoje  = new Date();

        const total     = end - start;
        const decorrido = hoje - start;

        let progresso = total > 0 ? (decorrido / total) * 100 : 0;

        progresso = Math.max(0, Math.min(100, progresso));

        if (state === "FINALIZADO") progresso = 100;

        bar.style.width = progresso.toFixed(1) + "%";

        let texto, corBarra, corTexto;

        if (state === "FINALIZADO") {
            texto    = "FINALIZADO";
            corBarra = CORES.barra.finalizado;
            corTexto = CORES.texto.principal;

        } else if (hoje > end) {
            texto    = "ATRASADO";
            corBarra = CORES.barra.atrasado;
            corTexto = CORES.texto.principal;

        } else if (hoje < start) {
            texto    = "NÃO INICIADO";
            corBarra = CORES.barra.naoIniciado;
            corTexto = CORES.texto.suave;

        } else {
            texto    = "EM ANDAMENTO — " + progresso.toFixed(0) + "%";
            corBarra = CORES.barra.andamento;
            corTexto = CORES.texto.secundario;
        }

        bar.style.backgroundColor = corBarra;
        label.textContent = texto;
        label.style.color = corTexto;

        label.dataset.status = state || "";
    });

});

document.getElementById("task-form").addEventListener("submit", (e) => {
    const campos = e.target.querySelectorAll("input[required], select[required]");
    let valido = true;

    campos.forEach(campo => {
        const erro = document.getElementById("erro-" + campo.name);
        if (!campo.value.trim()) {
            campo.classList.add("invalido");
            if (erro) {
                erro.textContent = "Adicione um título";
                erro.classList.add("ativo");
            }
            valido = false;
        } else {
            campo.classList.remove("invalido");
            if (erro) erro.classList.remove("ativo");
        }
    });

    if (!valido) e.preventDefault();
});